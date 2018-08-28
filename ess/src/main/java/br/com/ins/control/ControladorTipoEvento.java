package br.com.ins.control;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ins.core.TipoEvento;
import br.com.ins.dao.TipoEventoDAO;

@Stateless
public class ControladorTipoEvento implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@EJB
	TipoEventoDAO daoTipoEvento;
	
	public List<TipoEvento> tipoEventoMaisProcurados(Date dataInicio, Date dataFim) {
		List<TipoEvento> listaTiposEvento = daoTipoEvento.tipoEventoMaisProcurados(dataInicio, dataFim);
			return listaTiposEvento;

	}

}
