class Agenda {
	public No raiz;

	public Agenda(){
		try {
			inserirArvore('M');inserirArvore('F');inserirArvore('T');
			inserirArvore('C');inserirArvore('I');inserirArvore('P');
			inserirArvore('W');inserirArvore('A');inserirArvore('B');
			inserirArvore('D');inserirArvore('E');inserirArvore('G');
			inserirArvore('H');inserirArvore('J');inserirArvore('K');
			inserirArvore('L');inserirArvore('N');inserirArvore('O');
			inserirArvore('Q');inserirArvore('R');inserirArvore('S');
			inserirArvore('U');inserirArvore('V');inserirArvore('X');
			inserirArvore('Y');inserirArvore('Z');
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public void inserirArvore(char letra) throws Exception{
		raiz = inserir(letra, raiz);
	}

	private No inserir(char x, No i) throws Exception {
		if (i == null) {
         	i = new No(x);

	    } else if (x < i.letra) {
	        i.esq = inserir(x, i.esq);

	    } else if (x > i.letra) {
	        i.dir = inserir(x, i.dir);

	    } else {
	        throw new Exception("Erro ao inserir!");
	    }

		return i;
	}

	public boolean inserir(Contato contato) throws Exception{
		boolean resp = false;
		if(Character.isLetter(contato.nome.charAt(0))) {
			raiz = inserir(raiz, contato);
			resp = true;
		} else {
			resp = false;
			throw new Exception("O nome do contato esta invalido");
		}
		return resp;
	}

	public No inserir (No no, Contato contato) {
		//se nao existir o no com a letra
		if (no == null){
			no = new No(Character.toUpperCase(contato.nome.charAt(0)));
			no.primeiro.prox = new Celula(contato);
			no.ultimo = no.ultimo.prox;

		//se a primeira letra for igual ao no analisado	
		} else if (Character.toUpperCase(contato.nome.charAt(0)) == no.letra){
			no.ultimo.prox = new Celula(contato);
			no.ultimo = no.ultimo.prox;

		//se a primeira letra for menor	
		} else if (Character.toUpperCase(contato.nome.charAt(0)) < no.letra) {
			no.esq = inserir(no.esq, contato);

		//se a primeira letra for maior	
		} else {
			no.dir = inserir(no.dir, contato);
		}
		return no;
	}

	public boolean remover(String nome)throws Exception{
		boolean resp = false;
		raiz = remover(raiz, nome);
		resp = true;	
		return resp;
	}
//||
	public No remover(No no, String nome) throws Exception{
		//MyIO.println(no.letra);
		if (Character.toUpperCase(nome.charAt(0)) == no.letra){			
			Celula i = new Celula();
			for (i = no.primeiro; i.prox != null && i.prox.contato.nome != nome; i = i.prox);
			Celula tmp = i.prox;	
			if (tmp == no.primeiro.prox) {
				no.primeiro.prox = tmp.prox; 
			}			
			if (tmp == no.ultimo) {
				no.ultimo = i;
			} else {
				i.prox = i.prox.prox;						
			}
			tmp.prox = null;
			i = tmp = null;		
			
		} else if (Character.toUpperCase(nome.charAt(0)) < no.letra) {
			remover(no.esq, nome);
		} else if (Character.toUpperCase(nome.charAt(0)) > no.letra) {
			remover(no.dir, nome);
		} else {
			throw new Exception("Contato nao esta na lista");
		}
		return(no);
	}

	public boolean pesquisar(String nome) throws Exception{
		return pesquisar(raiz, nome);
	}

	public boolean pesquisar(No no, String nome) throws Exception{
		boolean resp = false;
		if (Character.toUpperCase(nome.charAt(0)) == no.letra){
			Celula i = new Celula();
			for (i = no.primeiro; i.prox != null && i.prox.contato.nome != nome; i = i.prox);
			Celula tmp = i.prox;
			if (tmp.contato.nome == nome) {			
				resp = true;
			} else {
				resp = false;
			}
		} else if (Character.toUpperCase(nome.charAt(0)) < no.letra) {
			resp = pesquisar(no.esq, nome);
		} else if (Character.toUpperCase(nome.charAt(0)) > no.letra) {
			resp = pesquisar(no.dir, nome);
		} else {
			resp = false;
		}
		return (resp);
	}

	public boolean pesquisar(int cpf){
		return (pesquisar (raiz, cpf));
	}


	public boolean pesquisar(No no, int cpf) {
		boolean resp = false;
		if (no != null) {
			resp = pesquisar(no.primeiro.prox, cpf);
			if(resp == false){
				resp = pesquisar(no.esq, cpf);
				if(resp == false){
					resp = pesquisar(no.dir, cpf);
				}
			}
		}
		return resp;
	}

	public boolean pesquisar (Celula i, int cpf) {
		boolean resp = false;
		Celula tmp;
		for (tmp = i; tmp != null && tmp.contato.cpf != cpf; tmp = tmp.prox);
		if (tmp.prox.contato.cpf != cpf) {
			resp = false;
		} else {
			resp = true;
		}

		return (resp);
	}

}