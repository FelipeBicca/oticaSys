package com.felipebicca.otica.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.felipebicca.otica.domain.Pessoa;
import com.felipebicca.otica.dto.PessoaNewDTO;
import com.felipebicca.otica.repositories.PessoaRepository;
import com.felipebicca.otica.resources.exceptions.FieldMessage;
import com.felipebicca.otica.services.validation.utils.BR;

public class PessoaInsertValidator implements ConstraintValidator<PessoaInsert, PessoaNewDTO> {

	@Autowired
	private PessoaRepository repo;

	@Override
	public void initialize(PessoaInsert ann) {
	}

	@Override
	public boolean isValid(PessoaNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		Set<String> emails = objDto.getEmails();

		for (String emailAux : emails) {
			Pessoa aux = repo.findByEmails(emailAux);

			if (aux != null) {
				list.add(new FieldMessage("Email", emailAux + " já existente"));
			}
		}

		if (!BR.isValidCPF(objDto.getCpf())) {
			list.add(new FieldMessage("cpf", "CPF inválido"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}

}