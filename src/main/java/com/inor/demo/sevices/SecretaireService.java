package com.inor.demo.sevices;

import java.util.List;

import com.inor.demo.shared.dto.SecretaireDto;

public interface SecretaireService {

	SecretaireDto createUser(SecretaireDto secretaireDto);

	SecretaireDto getSecretaire(String id);

	List<SecretaireDto> getSecretaires(int page, int limit);

	SecretaireDto updateSecretaire(String id, SecretaireDto secretaireDto);

	void deleteSecretaire(String id);
	

}
