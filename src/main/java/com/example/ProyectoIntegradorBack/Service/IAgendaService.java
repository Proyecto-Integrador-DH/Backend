package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.DTOs.AgendaDTO;

import java.util.Date;
import java.util.List;

public interface IAgendaService {
    AgendaDTO getAgendaById(Integer id);
    AgendaDTO postAgenda(AgendaDTO agendaDTO);
    List<AgendaDTO> allAgendas();
    List<AgendaDTO> getAgendasByProductId(Integer id);
    List<AgendaDTO> getAgendasByFechas(Date fechaIda, Date fechaVuelta);
}
