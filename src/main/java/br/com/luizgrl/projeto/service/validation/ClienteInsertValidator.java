package br.com.luizgrl.projeto.service.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.luizgrl.projeto.domain.Cliente;
import br.com.luizgrl.projeto.domain.enums.TipoCliente;
import br.com.luizgrl.projeto.dto.NewClienteDTO;
import br.com.luizgrl.projeto.repositories.ClienteRepository;
import br.com.luizgrl.projeto.resources.exceptions.FieldMensage;
import br.com.luizgrl.projeto.service.validation.utils.BR;
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, NewClienteDTO> {
 @Override
 public void initialize(ClienteInsert ann) {
}

@Autowired
ClienteRepository clienteRepository;
@Override
public boolean isValid(NewClienteDTO objDto, ConstraintValidatorContext context) { // verifica se o metodo é valido
List<FieldMensage> list = new ArrayList<>();

if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod())&& !BR.isValidCpf(objDto.getCpfOrCnpj())){
    list.add(new FieldMensage("cpfOrCnpj","CPF INVALIDO"));
}
if(objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod())&& !BR.isValidCnpj(objDto.getCpfOrCnpj())){
    list.add(new FieldMensage("cpfOrCnpj","CNPJ INVALIDO"));
}
Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
if(aux != null){
    list.add( new FieldMensage("email","Email já foi registrado "));

}


for (FieldMensage e : list) { 
context.disableDefaultConstraintViolation();
context.buildConstraintViolationWithTemplate(e.getMensage())
.addPropertyNode(e.getFieldName()).addConstraintViolation();
}
return list.isEmpty();
}
}


// o codigo tem uma lista de FIelMessage com o intuito de ter os erros adicionados e retornar uma mensagem porem
// é necesssario o for para adicionar os erros reconhecidos pelo framework