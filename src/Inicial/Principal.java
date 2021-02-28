package Inicial;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import classe.Cafe;
import classe.Pessoa;
import classe.SalaEvento;

public class Principal {

	

	public static List<Pessoa> pessoas;
	public static List<SalaEvento> eventos;
	public static List<Cafe> cafes;
	public static int salaInserido;
	public static int cafeInserido;


	public static void main(String[] args) {

		cafeInserido = -1;
		salaInserido = -1;
		pessoas = new ArrayList<Pessoa>();
		eventos = new ArrayList<SalaEvento>();
		cafes = new ArrayList<Cafe>();

		menu();

	}

	static void menu() {

		String opcao; 
		Scanner entrada = new Scanner(System.in);


		System.out.println("Menu");
		System.out.println("[1]cadastrar Café");
		System.out.println("[2]cadastrar sala");
		System.out.println("[3]cadastrar pessoa");
		System.out.println("[4]consultar sala café");
		System.out.println("[5]consultar sala");
		System.out.println("[6]consultar pessoa");
		System.out.println("[0]sair");
		
		opcao = entrada.nextLine();

		switch (opcao) {

			case "1":{
				cadastrarCafe();
				break;
			}

			case "2":{
				cadastrarSala();
				break;
			}
			case "3":{
				cadastrarPessoa();
				break;
			}
			case "4":{
				consultarCafe();
				break;
			}
			case "5":{
				consultarSala();
				break;
			
			}
			case "6":{
				consultaPessoas();
				break;
				
			}
			case "0":{
				System.out.println("obrigado por usar o software");
				System.exit(0);
				break;
			}
			default:{
				System.out.println("opção incorreta, escolha outra opção ");
				menu();
				break;
			}
			
		}
	
	}
		
		static ArrayList<Pessoa> subLista(ArrayList<Pessoa> lista, int inicio, int fim) {
		
		ArrayList<Pessoa> subList = new ArrayList<Pessoa>();
		
		for(int i = inicio; i < fim; i++) {
			subList.add(lista.get(i));
		}
		
		return subList;
	}
	
	static void cadastrarPessoa() {
		
		Pessoa pessoa = new Pessoa();
		Scanner entrada = new Scanner(System.in);
			
		if (eventos.size()==0 || cafes.size()==0) {
			System.out.println("Por favor, cadastre sala de eventos e cafes ! ");
			System.out.println("pressione enter para voltar para o menu ");
			entrada.nextLine();
			menu();
			
		}
		
		salaInserido = salaInserido +1;
		
		if (salaInserido >= eventos.size()) {
			salaInserido = 0;
		}
		
		if(eventos.get(salaInserido).getParticipantesH1().size() >= eventos.get(salaInserido).getLotacao() ) {
			System.out.println("lotação maxima da sala excedido ! aguarde um proximo evento.");
			menu();
		}
		cafeInserido = cafeInserido +1;
		if (cafeInserido >= cafes.size()) {
		
			cafeInserido = 0;
		
		}
		
		if(cafes.get(cafeInserido).getParticipantesH1().size() >= cafes.get(cafeInserido).getLotacao() ) {
			System.out.println("lotação maxima da sala excedido !");
			menu();
		}
		
		System.out.println("digite o nome da pessoa para ser cadastrada");
		pessoa.setNome(entrada.nextLine());
		System.out.println("digite o sobre nome da pessoa");
		pessoa.setSobrenome(entrada.nextLine());
		
		pessoas.add(pessoa);
		eventos.get(salaInserido).inserirPessoaH1(pessoa);
		
		cafes.get(cafeInserido).inserirPessoaH1(pessoa);
		
		for(int i = 0; i < eventos.size(); i++ ) {
			eventos.get(i).setParticipantesH2(new ArrayList<Pessoa>());
		}
		
		for(int i = 0; i < eventos.size(); i++ ) {
			
			ArrayList<Pessoa> alocados = new ArrayList<Pessoa>();
			ArrayList<Pessoa> realocados = new ArrayList<Pessoa>();
			
			if(eventos.get(i).getParticipantesH1().size() == 1) {
				realocados.add(eventos.get(i).getParticipantesH1().get(0));
			}else if(eventos.get(i).getParticipantesH1().size() > 1){
				alocados = subLista(eventos.get(i).getParticipantesH1(),0, (int)Math.floor(eventos.get(i).getParticipantesH1().size()/2));
				realocados = subLista(eventos.get(i).getParticipantesH1(),(int)Math.floor(eventos.get(i).getParticipantesH1().size()/2),eventos.get(i).getParticipantesH1().size());
			}			
			
			if( i + 1 < eventos.size()) {
				for(Pessoa p: alocados) {
					eventos.get(i).inserirPessoaH2(p);
				}
				for(Pessoa p: realocados) {
					eventos.get(i+1).inserirPessoaH2(p);
				}
			}else if(i + 1 == eventos.size()){
				for(Pessoa p: alocados) {
					eventos.get(i).inserirPessoaH2(p);
				}
				for(Pessoa p: realocados) {
					eventos.get(0).inserirPessoaH2(p);
				}
			}
			
		}
				
		for(int i = 0; i < cafes.size(); i++ ) {
			cafes.get(i).setParticipantesH2(new ArrayList<Pessoa>());
		}
		
		for(int i = 0; i < cafes.size(); i++ ) {
			
			ArrayList<Pessoa> alocados = new ArrayList<Pessoa>();
			ArrayList<Pessoa> realocados = new ArrayList<Pessoa>();
			
			if(cafes.get(i).getParticipantesH1().size() == 1) {
				realocados.add(cafes.get(i).getParticipantesH1().get(0));
			}else if(cafes.get(i).getParticipantesH1().size() > 1){
				alocados = subLista(cafes.get(i).getParticipantesH1(),0, (int)Math.floor(cafes.get(i).getParticipantesH1().size()/2));
				realocados = subLista(cafes.get(i).getParticipantesH1(),(int)Math.floor(cafes.get(i).getParticipantesH1().size()/2),cafes.get(i).getParticipantesH1().size());
			}			
			
			if( i + 1 < cafes.size()) {
				for(Pessoa p: alocados) {
					cafes.get(i).inserirPessoaH2(p);
				}
				for(Pessoa p: realocados) {
					cafes.get(i+1).inserirPessoaH2(p);
				}
			}else if(i + 1 == cafes.size()){
				for(Pessoa p: alocados) {
					cafes.get(i).inserirPessoaH2(p);
				}
				for(Pessoa p: realocados) {
					cafes.get(0).inserirPessoaH2(p);
				}
			}
			
		}
		
		menu();
	
	}
	static void cadastrarSala() {
	
		
		
		Scanner entrada = new Scanner(System.in);
		SalaEvento evento = new SalaEvento();
		
		if (pessoas.size() >0 ) {
			System.out.println("Não é possivel cadastrar mais salas");
			menu();
		}
		
			
		
		System.out.println("digite o nome da sala");
		evento.setNome(entrada.nextLine()); 
		System.out.println("digite a quantidade de alunos");
		evento.setLotacao(entrada.nextInt());
		entrada.nextLine();
		eventos.add(evento);
		menu();
	
	}
	static void cadastrarCafe() {
	
		Scanner entrada = new Scanner(System.in);
		Cafe cafe = new Cafe();
		
		if (pessoas.size() >0 ) {
			System.out.println("Não é possivel cadastrar mais salas");
			menu();
		}
		if (cafes.size() == 2 ) {
			System.out.println("quantidade limite de espaço café já cadastrado");
			System.out.println("pressione enter para voltar para o menu");
			entrada.nextLine();
			
			menu();
		}
		System.out.println("Digite o nome do espaço café");
		cafe.setNome(entrada.nextLine());
		
		System.out.println("cadastre a quantidade de  pessoas no espaço de café");
		cafe.setLotacao(entrada.nextInt());
		cafes.add(cafe);
		
		menu();
	
	}

	static void consultaPessoas() {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Digite o numero corespondente ao participante");
		int contador = 0;
		for (Pessoa participante: pessoas) {
			contador = contador +1;
			System.out.printf("[%d] - %s %s  %n",contador,participante.getNome(),participante.getSobrenome());
		}
		
		System.out.println("Digite 0 para volta ao menu");
		int opcao = entrada.nextInt();
		entrada.nextLine();
		if (opcao == 0) {
			menu();
		}
		else if (opcao>pessoas.size()) {
			consultaPessoas();
		}
		else {
			consultarPessoa(opcao-1);
		}
		
	}

	static void consultarPessoa(int posicao) {
		Pessoa participante = pessoas.get(posicao);
		
		System.out.printf("Participante: %s %s %n", participante.getNome(),participante.getSobrenome());
		
		for (SalaEvento salaEvento : eventos) {
			for(Pessoa pessoa: salaEvento.getParticipantesH1()) {
				if(pessoa.getNome() == participante.getNome() && pessoa.getSobrenome() == participante.getSobrenome()){
					System.out.printf("Sala de Evento no primeiro horario eh: %s %n", salaEvento.getNome() );
				}
			}	
			
			for(Pessoa pessoa: salaEvento.getParticipantesH2()) {
				if(pessoa.getNome() == participante.getNome() && pessoa.getSobrenome() == participante.getSobrenome()){
					System.out.printf("Sala de Evento no segundo horario eh: %s %n", salaEvento.getNome() );
				}
			}
		}
		
		for (Cafe espacoCafe : cafes) {
			for(Pessoa pessoa: espacoCafe.getParticipantesH1()) {
				if(pessoa.getNome() == participante.getNome() && pessoa.getSobrenome() == participante.getSobrenome()){
					System.out.printf("Espaco Cafe no primeiro horario eh: %s %n", espacoCafe.getNome() );
				}
			}	
			
			for(Pessoa pessoa: espacoCafe.getParticipantesH2()) {
				if(pessoa.getNome() == participante.getNome() && pessoa.getSobrenome() == participante.getSobrenome()){
					System.out.printf("Espaco Cafe no segundo horario eh: %s %n", espacoCafe.getNome() );
				}
			}	
		}
		Scanner entrada = new Scanner(System.in);
		System.out.println("Digite 1 para consultar um nova pessoa");
		System.out.println("Digite 0 para voltar ao menu");
		int opcao = entrada.nextInt();
		entrada.nextLine();
		if (opcao==1) {
			consultaPessoas();
		}
		menu();
	}

	
	static void consultarSala() {
		
		int contador = 0;
		
		System.out.println("digite o numero correspondente a sala de eventos para ver os participantes");
		
		for (SalaEvento evento : eventos ) {
			contador = contador +1;
			System.out.printf("sala de evento [%d] : %s - lotação: %d / %d %n",contador,evento.getNome(),evento.getParticipantesH1().size(),evento.getLotacao());
		
		}
		
	
		System.out.println("pressione 0 para voltar para o menu");
		Scanner entrada = new Scanner (System.in);
		int opcao = entrada.nextInt();
		entrada.nextLine();
		
		if (opcao == 0){
			menu();	
		}else if(opcao > eventos.size())	{
			consultarSala();
		}else {
			consultarPessoasSala(opcao -1);
		}
			
		
	}
	
	static void consultarPessoasSala (int numeroSala) {
		SalaEvento salaAtual = eventos.get(numeroSala);
		ArrayList <Pessoa> participantes = salaAtual.getParticipantesH1() ; 
		
		System.out.printf("participantes da sala %s %n",salaAtual.getNome());
		
		
		for (Pessoa participante : participantes) {
			System.out.printf("nome: %s sobrenome: %s %n",participante.getNome(),participante.getSobrenome() );
		}
		
		System.out.println("pressione [1] para voltar menu");
		System.out.println("pressione [2] para voltar consultas de sala");
		Scanner entrada = new Scanner(System.in);
		int opcao = entrada.nextInt();
		if (opcao == 1) {
			menu();	
		}else if(opcao == 2) {
			consultarSala();
		}else {
			System.out.println("opção invalida ! escolha outra opção");
			consultarPessoasSala(numeroSala);
		}
		
	}
	
	static void consultarCafe() {
		
		int contador = 0;
		
		System.out.println("digite o numero correspondente ao espaço cafe para ver os participantes");
		
		for (Cafe cafe : cafes ) {
			contador = contador +1;
			System.out.printf("espaço café [%d] : %s - lotação: %d / %d %n",contador,cafe.getNome(),cafe.getParticipantesH1().size() ,cafe.getLotacao());
		
		}
		
		System.out.println("pressione 0 para voltar para o menu");
		Scanner entrada = new Scanner (System.in);
		int opcao = entrada.nextInt();
		entrada.nextLine();
		
		if (opcao == 0){
			menu();	
		}else if(opcao > cafes.size())	{
			consultarCafe();
		}else {
			consultarPessoasCafe(opcao -1);
		}
		
	}
	
	static void consultarPessoasCafe (int numeroSala) {
		Cafe cafeAtual = cafes.get(numeroSala);
		ArrayList <Pessoa> participantes = cafeAtual.getParticipantesH1() ; 
		
		System.out.println("participantes do cafe");
		
		for (Pessoa participante : participantes) {
			System.out.printf("nome: %s sobrenome: %s %n",participante.getNome(),participante.getSobrenome() );
		}
		
		System.out.println("pressione [1] para voltar menu");
		System.out.println("pressione [2] para voltar consultas de sala");
		Scanner entrada = new Scanner(System.in);
		int opcao = entrada.nextInt();
		if (opcao == 1) {
			menu();	
		}else if(opcao == 2) {
			consultarCafe();
		}else {
			System.out.println("opção invalida ! escolha outra opção");
			consultarPessoasCafe(numeroSala);
		}
		
	}
}
