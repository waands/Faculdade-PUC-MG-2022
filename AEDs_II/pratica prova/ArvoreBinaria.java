class ArvoreBinaria {
	public No raiz;

	public void ArvoreBinaria(){
		raiz =null;
	}

	public void inserir(int elemento){
		raiz = inserir(elemento, raiz);
	}

	public No inserir(int x, No i){
		if(i.elemento == null){
			i = new No(x);
		} else if (x > i.elemento){
			i = inserir(x, i.dir);
		} else if (x < i.elemento){
			i = inserir(x, i.esq);
		}

		return(i);
	}

	public boolean pesquisar(int elemento){
		return(pesquisar(elemento, raiz));
	}

	public boolean pesquisar(int x, No i){
		boolean resp;
		if(i.elemento == null){
			resp = false;
		} else if (x > i.elemento) {
			resp = pesquisar(x, i.dir);
		} else if (x > i.elemento) {
			resp = pesquisar(x, i.esq);
		}

		return resp;
	}

	public void caminharCentral(){
		caminharCentral(raiz);
	}

	public void caminharCentral(No i){
		if (i != null){
			caminharCentral(i.esq);
			System.out.println(i.elemento);
			caminharCentral(i.dir);
		}
	}

	public void caminharPos(){
		caminharPos(raiz);
	}

	public void caminharPos(No i){
		if (i != null) {
			caminharPos(i.esq);
			caminharPos(i.dir);
			System.out.println(i.elemento);
		}
	}

	public void caminharPre(){
		caminharPre(raiz);
	}

	public void caminharPre(No i){
		if (i != null) {
			caminharPre(i.dir);
			caminharPre(i.esq);
			System.out.println(i.elemento);
		}
	}

	public int getAltura() {
		int altura = getAltura(raiz, altura);
		return altura;
	}

	public int getAltura(No i, int altura) {
		if (i == null){
			altura--;
		} else {
			int alturaEsq = getAltura(i.esq, altura + 1);
			int alturaDir = getAltura(i.dir, altura + 1);
			if (alturaEsq > alturaDir){
				altura = alturaEsq;
			} else {
				altura = alturaDir;
			}
		}
		return altura;
	}

	public int soma() {
		return soma(raiz);
	}

	public int soma(No i) {
		int soma = 0;
		if (i != null){
			resp = i.elemento + soma(i.esq) + soma(i.dir);
		}
		return resp;
	}

	public int numPares(){
		return numPares(raiz);
	}

	public int numPares(No i){
		int pares = 0;
		if (i != null){
			if (i.elemento % 2 == 0){
				pares++;
			}
			pares += numPares(i.esq) + numPares(i.dir);
		}
		return pares;
	}

	public static boolean igual (ArvoreBinaria a1, ArvoreBinaria a2){
		return igual(a1.raiz, a2.raiz);
	}

	public static boolean igual (No i1, No i2){
		boolean resp;
		if(i1 != null && i2 != null){
			resp = (i1.elemento == i2.elemento) && igual(i1.esq, i2.esq) && igual(i1.dir, i2.dir);
		} else if (i1 == null && is == null){
			resp = true;
		} else {
			resp = false;
		}
		
		return resp;
	}
}