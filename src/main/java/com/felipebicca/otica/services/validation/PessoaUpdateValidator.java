package com.felipebicca.otica.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.felipebicca.otica.domain.Pessoa;
import com.felipebicca.otica.dto.PessoaDTO;
import com.felipebicca.otica.repositories.PessoaRepository;
import com.felipebicca.otica.resources.exceptions.FieldMessage;

public class PessoaUpdateValidator implements ConstraintValidator<PessoaUpdate, PessoaDTO> {

	@Autowired
	private PessoaRepository repo;

	@Autowired
	HttpServletRequest request;

	@Override
	public void initialize(PessoaUpdate ann) {
	}

	@Override
	public boolean isValid(PessoaDTO objDto, ConstraintValidatorContext context) {

		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();

		Pessoa aux = repo.findByEmails(objDto.getEmails().get(0));

		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("Email", "E-mail já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}