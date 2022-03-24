package com.inor.demo.sevices;

import java.util.List;

import com.inor.demo.shared.dto.SeanceDto;

public interface SeanceService {

	SeanceDto createSeance(SeanceDto seanceDto);

	SeanceDto getSeance(String id);

	List<SeanceDto> getSeances(int page, int limit);

	SeanceDto updateSeance(String id, SeanceDto seanceDto);

	void deleteSeance(String id);

}
