package br.com.luizgrl.projeto.service;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import br.com.luizgrl.projeto.config.SecurityConfig;
import br.com.luizgrl.projeto.domain.enums.Perfil;
import br.com.luizgrl.projeto.security.UserSS;
import br.com.luizgrl.projeto.service.exceptions.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.luizgrl.projeto.domain.Cidade;
import br.com.luizgrl.projeto.domain.Cliente;
import br.com.luizgrl.projeto.domain.Endereco;
import br.com.luizgrl.projeto.domain.enums.TipoCliente;
import br.com.luizgrl.projeto.dto.ClienteDTO;
import br.com.luizgrl.projeto.dto.NewClienteDTO;
import br.com.luizgrl.projeto.repositories.ClienteRepository;
import br.com.luizgrl.projeto.repositories.EnderecoRepository;
import br.com.luizgrl.projeto.service.exceptions.DataIntegrityException;
import br.com.luizgrl.projeto.service.exceptions.ObjectNotFoundException;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private  S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.prefix.client.profile}")
    private  String prefix;

    @Value("${img.profile.size}")
    private  Integer size;

    public Cliente find(Integer id) {
        UserSS user = UserService.authenticated(); // vai buscar o usuario para verificiar se o mesmo é admin
        if(user==null||!user.hasRole(Perfil.ADMIN) &&!id.equals(user.getId())){ // vai buscar um usuario que nao seja nulo que seja admin e que se o id for diferente do usuario logado
            throw  new AuthorizationException("Acesso negado");
        }
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Cliente id: " + id + " Não foi encontrado. Tipo: " + Cliente.class.getName()));
    }

    public Cliente update(Cliente obj){
        find(obj.getId());
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return clienteRepository.save(newObj);
    }
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public void delete(Integer id){
        find(id);
        try{
            clienteRepository.deleteById(id);
        }catch(DataIntegrityViolationException event){
            throw new DataIntegrityException("Erro ao deletar cliente", event);

        }
    }

    public Cliente fromDTO(ClienteDTO objDto){
        return new Cliente(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null,null);
    }

    public Cliente fromDTO(NewClienteDTO objDto) { // Usado para converter de DTO para categoria
        Cliente cliente = new Cliente(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(), TipoCliente.toEnum(objDto.getTipoCliente()),securityConfig.bCryptPasswordEncoder().encode(objDto.getPassword()));
        Cidade cidade = new Cidade(objDto.getCidadeId(),null,null);
        Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cliente, cidade);
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(objDto.getTelefone1());
        if(objDto.getTelefone2()!=null){
            cliente.getTelefones().add(objDto.getTelefone2());
        }if(objDto.getTelefone3()!=null){
            cliente.getTelefones().add(objDto.getTelefone3());
        }
        return cliente;
    }

    private void updateData(Cliente newObj, Cliente obj){
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null); 
        obj = clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;

    }

    public URI uploadProfilePicture(MultipartFile multipartFile){
        UserSS user = UserService.authenticated();
        if(user==null){
            throw  new AuthorizationException("Acesso negado");
        }

        BufferedImage jpgImage = imageService.getJpgFromPngFile(multipartFile);
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage,size);

        String fileName = prefix + user.getId() + ".jpg";
        return  s3Service.updloadFile(imageService.getInputStream(jpgImage,"jpg"),fileName,"image");

    }


}
