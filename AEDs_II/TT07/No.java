class No {
	public char letra;
	public No esq, dir;
	public Celula primeiro, ultimo;

	public No() {
		this.letra = ' ';
		this.esq = this.dir = null;
		this. primeiro = this.ultimo = null;
	}

	public No (char letra){
		this.letra = letra;
		this.esq = this.dir = null;
		primeiro = ultimo = new Celula();
	}

}