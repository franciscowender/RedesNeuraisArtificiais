/**
 * 
 */
package br.ufpi.easii.camadas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufpi.easii.funcaoDeAtivacao.AbstractFunction;
import br.ufpi.easii.neuronios.Neuronio;

/**
 * @author Ronyerison
 * 
 */
public class Camada {
	protected List<Neuronio> neuronios;
	protected Double[] vetorEntrada;
	protected Double[] vetorSaida;
	protected Integer quantNeuronios;
	protected Double[] vetorGradiente;
	protected Double gradienteCamada;

	public Camada(int quantNeuronios) {
		this.quantNeuronios = quantNeuronios;
		neuronios = new ArrayList<Neuronio>();
		vetorEntrada = new Double[quantNeuronios];
		vetorSaida = new Double[quantNeuronios];
		vetorGradiente = new Double[quantNeuronios];
		this.adicionarNeuronios();
	}

	public void adicionarNeuronios() {
		for (int i = 0; i < this.quantNeuronios; i++) {
			Neuronio neuronio = new Neuronio();
			this.neuronios.add(neuronio);
		}
	}

	public void combinarEntradas(Double[] entradas) {
		for (int i = 0; i < this.quantNeuronios; i++) {
			neuronios.get(i).somatorio(entradas);
			this.vetorEntrada[i] = neuronios.get(i).getPotencialDeAtivacao();
		} 
	}
	
	public void gerarSaidas(){
		for (int i = 0; i < this.neuronios.size(); i++) {
			this.neuronios.get(i).ativarNeuronio();
			this.vetorSaida[i] = neuronios.get(i).getSaida();
		}
	}

	public void somarGradiente(){
		gradienteCamada = 0.0;
		for (int i = 0; i < neuronios.size(); i++) {
			for (int j = 0; j < neuronios.get(i).getPesos().length; j++) {
				gradienteCamada += vetorGradiente[i] * neuronios.get(i).getPesos()[j];
			}
		}
		
	}
	
	public void gerarPesos(int quantEntradas){
		for (int i = 0; i < this.getQuantNeuronios(); i++) {
			this.neuronios.get(i).gerarPesos(quantEntradas);
		}
	}
	
	public void ajustarPesos(Double taxaDeAprendizado, Double[] vetorEntrada){
		for (int i = 0; i < this.getQuantNeuronios(); i++) {
			this.neuronios.get(i).ajustarPesos(taxaDeAprendizado, vetorEntrada, vetorGradiente[i]);
		}
	}
	
	public void ajustarPesosRandom(Double rand){
		for (int i = 0; i < this.getQuantNeuronios(); i++) {
			this.neuronios.get(i).ajustarPesosRandom(rand);
		}
	}
	
	/**
	 * @return the neuronios
	 */
	public List<Neuronio> getNeuronios() {
		return neuronios;
	}

	/**
	 * @param neuronios the neuronios to set
	 */
	public void setNeuronios(List<Neuronio> neuronios) {
		this.neuronios = neuronios;
	}

	/**
	 * @return the vetorEntrada
	 */
	public Double[] getVetorEntrada() {
		return vetorEntrada;
	}

	/**
	 * @param vetorEntrada the vetorEntrada to set
	 */
	public void setVetorEntrada(Double[] vetorEntrada) {
		this.vetorEntrada = vetorEntrada;
	}

	/**
	 * @return the vetorSaida
	 */
	public Double[] getVetorSaida() {
		return vetorSaida;
	}

	/**
	 * @param vetorSaida the vetorSaida to set
	 */
	public void setVetorSaida(Double[] vetorSaida) {
		this.vetorSaida = vetorSaida;
	}

	/**
	 * @return the quantNeuronios
	 */
	public Integer getQuantNeuronios() {
		return quantNeuronios;
	}

	/**
	 * @param quantNeuronios the quantNeuronios to set
	 */
	public void setQuantNeuronios(Integer quantNeuronios) {
		this.quantNeuronios = quantNeuronios;
	}

	/**
	 * @return the vetorGradiente
	 */
	public Double[] getGradiente() {
		return vetorGradiente;
	}

	/**
	 * @param vetorGradiente the vetorGradiente to set
	 */
	public void setGradiente(Double[] vetorGradiente) {
		this.vetorGradiente = vetorGradiente;
	}

	public void setFuncaoDeAtivacao(AbstractFunction abstractFunction){
		for (Neuronio neuronio : neuronios) {
			neuronio.setFuncaoDeAtivacao(abstractFunction);
		}
	}
	public Double[][] getPesos(){
		Double[][] matriz = new Double[quantNeuronios][vetorEntrada.length];
		for (int i = 0; i < quantNeuronios; i++) {
			matriz[i] = this.neuronios.get(i).getPesos();
		}
		return matriz;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Camada [\nvetorEntrada="
				+ Arrays.toString(vetorEntrada) + ", \nvetorSaida="
				+ Arrays.toString(vetorSaida) + ", \nquantNeuronios="
				+ quantNeuronios + ", \nvetorGradiente="
				+ Arrays.toString(vetorGradiente) + ", \ngradienteCamada="
				+ gradienteCamada + "]";
	}
	
	
	
}
