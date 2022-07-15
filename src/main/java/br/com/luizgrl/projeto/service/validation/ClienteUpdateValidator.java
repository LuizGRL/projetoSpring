package br.com.luizgrl.projeto.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.luizgrl.projeto.domain.Cliente;
import br.com.luizgrl.projeto.dto.ClienteDTO;
import br.com.luizgrl.projeto.repositories.ClienteRepository;
import br.com.luizgrl.projeto.resources.exceptions.FieldMensage;
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
 @Override
 public void initialize(ClienteUpdate ann) {
}

@Autowired
private ClienteRepository clienteRepository;

@Autowired
private HttpServletRequest request; // vai ser utilizado para pegar o id do cliente
@Override
public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) { // verifica se o metodo é valido
List<FieldMensage> list = new ArrayList<>();
@SuppressWarnings("unchecked") // para tirar o erro amarelo 
Map<String,String> map = (Map <String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
// vai ser utilizado para pegar os atributos que vem na requisição 
Integer uriId = Integer.parseInt( map.get("id"));
Cliente aux = clienteRepository.findByEmail(objDto.getEmail());

if(aux != null && aux.getId().equals(uriId)){
    list.add(new FieldMensage("email","Email ja foi adicionado a outro usuario "));

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