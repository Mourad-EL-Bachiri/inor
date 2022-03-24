package com.inor.demo.sevices;

import java.util.List;

import com.inor.demo.shared.dto.NiveauDto;

public interface NiveauService {

	NiveauDto createNiveau(NiveauDto niveauDto);

	NiveauDto getNiveau(String id);

	List<NiveauDto> getNiveaux(int page, int limit);

	NiveauDto updateNiveau(String id, NiveauDto niveauDto);

	void deleteNiveau(String id);

}
