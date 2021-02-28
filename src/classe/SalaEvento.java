package classe;

import java.util.ArrayList;


public class SalaEvento {

	
	private ArrayList <Pessoa> participantesH1;
	private ArrayList <Pessoa> participantesH2;
	
	private String nome;
	private int lotacao;

	public SalaEvento() {
		this.nome="";
		this.lotacao=0;
		this.participantesH1 = new ArrayList<Pessoa>();
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getLotacao() {
		return lotacao;
	}

	public void setLotacao(int lotacao) {
		this.lotacao = lotacao;
	}
	public void inserirPessoaH1(Pessoa participanteH1) {
		this.participantesH1.add(participanteH1);
	}

	public void inserirPessoaH2(Pessoa participanteH2) {
		this.participantesH2.add(participanteH2);
	}

	public ArrayList<Pessoa> getParticipantesH1() {
		return participantesH1;
	}

	public void setParticipantesH1(ArrayList<Pessoa> participantesH1) {
		this.participantesH1 = participantesH1;
	}

	public ArrayList<Pessoa> getParticipantesH2() {
		return participantesH2;
	}

	public void setParticipantesH2(ArrayList<Pessoa> participantesH2) {
		this.participantesH2 = participantesH2;
	}


	
	
}
