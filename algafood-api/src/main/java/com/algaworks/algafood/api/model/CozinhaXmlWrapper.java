package com.algaworks.algafood.api.model;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "cozinhas")
public class CozinhaXmlWrapper {

	@JsonProperty("cozinha")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Cozinha> cozinhas;

	public CozinhaXmlWrapper(List<Cozinha> cozinhas) {
		this.cozinhas = cozinhas;
	}

	public List<Cozinha> getCozinhas() {
		return cozinhas;
	}

	public void setCozinhas(List<Cozinha> cozinhas) {
		this.cozinhas = cozinhas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cozinhas == null) ? 0 : cozinhas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CozinhaXmlWrapper other = (CozinhaXmlWrapper) obj;
		if (cozinhas == null) {
			if (other.cozinhas != null)
				return false;
		} else if (!cozinhas.equals(other.cozinhas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CozinhaXmlWrapper [cozinhas=" + cozinhas + "]";
	}

}
