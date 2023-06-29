/**
 * Author: Wanderson Teixeira - 783518
 * TP02 - Classe Game em Java
 */ 

import java.util.Arrays;
import java.util.Locale;
import java.util.*;
import java.text.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameListDoublFlexQuick {
    /**
    * isFim - Verifica o fim das entradas
    * @param  {String}
    * @return {boolean}
    */
    public static boolean isFim (String s){
        return (s.length()==3 && s.charAt(0)=='F' &&
            s.charAt(1)=='I' && s.charAt(2)=='M');
    }

    /**
     * main - open the games.csv, read the inputs, call the methods
     */ 
    public static void main (String[] args) {
        long startTime = System.nanoTime();
        int comparacoes = 0;

        BufferedReader objReader = null;
        String pubin = MyIO.readLine();
        Lista gameList = new Lista();    
         
        while (!isFim(pubin)) {
            try {
                String line;
                objReader = new BufferedReader(new FileReader("/tmp/games.csv"));
                while ((line = objReader.readLine()) != null) {
                    String[] gameId = line.split(",", 0);
                    Game jojo = new Game(gameId[0]);
                    if (gameId[0].equals(pubin)) {
                        jojo.createFromStr(line);
                        gameList.inserirFim(jojo);
                        //jojo.printGame();
                        
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            pubin = MyIO.readLine();
        }
/*
        int operacoes = Integer.parseInt(MyIO.readLine());
        

        
        for (int k = 0; k < operacoes; k++) {
            try{
                pubin = MyIO.readLine();    //ler o proximo comando da lista
                String input[] = pubin.split(" "); //separar nos espaços

                
                String line;
                Game tmp = new Game();
                objReader = new BufferedReader(new FileReader("/tmp/games.csv"));
                while ((line = objReader.readLine()) != null) {
                    String[] gameId = line.split(",", 0);
                    if (input.length == 2 && gameId[0].equals(input[1])){
                        tmp.createFromStr(line);
                    }
                    else if (input.length == 3 && gameId[0].equals(input[2])){
                        tmp.createFromStr(line);
                    }
                }
                
    
                while(i < size){
                    if (input.length == 2){
                       if (initialList.array[i].getApp_id() == Integer.parseInt(input[1]))
                        { j = i; }
                        else {i++;} 
                    } else  if (input.length == 3){
                        if (initialList.array[i].getApp_id() == Integer.parseInt(input[2]))
                        { j = i; }
                        else {i++;} 
                    }                        
                }
    
                int pos = 0;                

                if (input[0].compareTo("II") == 0) {            
                    gameList.inserirInicio(tmp);
                }
                else if (input[0].compareTo("IF") == 0) {             
                    gameList.inserirFim(tmp);
                }
                else if (input[0].compareTo("I*") == 0) {
                    pos = Integer.parseInt(input[1]);          
                    gameList.inserir(tmp, pos);
                }
                else if (input[0].compareTo("RF") == 0) {             
                    tmp = gameList.removerFim();
                    System.out.print("(R) " + tmp.getName() + "\n");
                }   
                else if (input[0].compareTo("RI") == 0) {             
                    tmp = gameList.removerInicio();
                    System.out.print("(R) " + tmp.getName() + "\n");
                }
                else if (input[0].compareTo("R*") == 0){
                    pos = Integer.parseInt(input[1]);  
                    tmp = gameList.remover(pos);
                    System.out.print("(R) " + tmp.getName() + "\n");
                }
                

            }catch (Exception e2) {
                e2.printStackTrace();
            }
        }
*/
        gameList.sort();
        
        gameList.imprimirLista();



        /*int i = 0;
        Game test = new Game();
        String strTest = "";
        strTest = MyIO.readLine();
        do {

            test.createFromStr(strTest);
            test.printGame();
            strTest = MyIO.readLine();
        } while (!isFim(strTest));*/
        
        long endTime = System.nanoTime();
        long tempoTotal = endTime - startTime;
        try{
            FileWriter fw = new FileWriter("matrícula_quicksort2.txt");
            fw.write("783518\t" + tempoTotal/1000000 + "ms\t" + comparacoes);
            fw.close();
        } catch (IOException e){
            MyIO.println("");
        }           
        
    }
}

class Celula {
    public Game elemento;
    public Celula prox, ant;

    public Celula() {
        this(null);
    }

    public Celula (Game x) {
        this.elemento = x;
        this.prox = this.ant = null;
    }
}

class Lista {
    private Celula primeiro, ultimo; //primeiro=sentinela // ultimo=controle

    public Lista () {
        primeiro = new Celula();
        ultimo = primeiro;//ultimo aponta pro primeiro pq ta vazio
    }

    public void inserirInicio(Game x) {
        Celula tmp = new Celula(x);
        tmp.ant = primeiro;
        tmp.prox = primeiro.prox; //tornar esse o primeiro apontando pro antigo primeiro
        primeiro.prox = tmp; //fazer a sentinela apontar pro novo primeiro

        if (primeiro == ultimo) {
            ultimo = tmp; //se a lista estiver vazia fazer o ultimo apontar pro novo primeiro
        } else {
            tmp.prox.ant = tmp;
        }

        tmp = null; //esvaziar o primeiro
    }

    public void inserirFim(Game x) {
        ultimo.prox = new Celula(x); //fazer o *ultimo apontar pro novo ultimo
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox; //o ultimo agora vai apontar pro novo *ultimo
    }

    public Game removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!"); //n tem como remover o primeiro se ela estiver vazia
        }

        Celula tmp = primeiro; //fazer o tmp apontar pro *primeiro
        primeiro = primeiro.prox; //tonar o segundo o novo primeiro
        Game resp = primeiro.elemento; //guardar pra retornar qual foi retirado
        tmp.prox = primeiro.ant = null; //esvaziar o proximo do tmp
        tmp = null; //esvaziar o tmp
        return resp; //retornar elemento removido
    }

    public Game removerFim() throws Exception{
        if (primeiro == ultimo) //se estiver vazia
            throw new Exception("Erro!"); //n da pra remover
        Game elemento = ultimo.elemento; //salvar pra retornar qual foi removido
        ultimo = ultimo.ant;
        ultimo.prox.ant = ultimo.prox = null;
        return elemento; //retornar elemento removido
    }

    public void inserir(Game x, int pos) throws Exception{
        int tamanho= tamanho(); //descobrir o tamanho da lista
        if (pos < 0 || pos > tamanho) {
            throw new Exception("Erro!"); //nao deixar inserir se nao for valido o valor
        } else if (pos == 0) {
            inserirInicio(x); //se a posicao for a primeira, chama o metodo de inserir no inicio
        } else if (pos == tamanho) {
            inserirFim(x); //se a posicao for a ultima, chama o metodo dee inserir no final
        } else {
            Celula i = primeiro; //criar celula apontando pro primeiro
            for (int j = 0; j < pos; j++, i = i.prox); //colocar o j 1 posicao antes da posicao que vai ser inserirda
                                                       //colocar o i apontando pra celula antes da posicao que vai ser inserida
            Celula tmp = new Celula(x); //criar celula que vai ser inserida
            tmp.ant = i;
            tmp.prox = i.prox; //fazer a nova celula apontar pro proximo corretamente
            tmp.ant.prox = tmp.prox.ant = tmp; //fazer a celula antes e depois da inserida apontar pra inserida
            tmp = i = null; //esvaziar o elemento i e o tmp 
        }
    }

    public Game remover(int pos) throws Exception{
        int tamanho = tamanho();
        Game elemento;
        if (primeiro == ultimo || pos < 0 || pos >= tamanho){
            throw new Exception("Erro!"); //nao deixar remover se nao for valido o valor
        } else if (pos == 0) {
            elemento = removerInicio(); //se a posicao for a primeira, chama o metodo de remover no inicio
        } else if (pos == tamanho - 1) {
            elemento = removerFim(); //se a posicao for a ultima, chama o metodo dee remover no final
        } else {
            Celula i = primeiro; //criar celula apontando pro primeiro
            for(int j = 0; j < pos; j++, i = i.prox); //colocar o j 1 posicao antes da posicao que vai ser inserirda
                                                      //colocar o i apontando pra celula antes da posicao que vai ser inserida
            i.ant.prox = i.prox; 
            i.prox.ant = i.ant;
            elemento = i.elemento; 
            i.prox = i.ant = null;
            i = null; // esvaziar o elemento removido
        }

        return elemento;
    }

    /**
    * Mostra os elementos da lista separados por espacos.
    */
   public void mostrar (){
      int tamanho = tamanho();

      System.out.print("[ ");
      
    for(Celula i = primeiro.prox; i == ultimo; i = i.prox) {

         i.elemento.printGame();
         MyIO.print(" ");
      }
      System.out.println("]");
    }

    public int tamanho() {
      int tamanho = 0; 
      for(Celula i = primeiro.prox; i != null; i = i.prox, tamanho++);
      return tamanho;
   }

   public void imprimirLista(){

      int tamanho = tamanho();
      int j = 0;
        for(Celula i = primeiro.prox; i != null; j++, i = i.prox) {
            //System.out.print("[" + j + "] ");
            i.elemento.printGame();
        }
    }

    public void sort() {
      quicksort(0, tamanho());
      sortE(tamanho());
    }

    private void sortE(int size){
        //MyIO.println("aqui");

        int k = 0;
        for (int z = 1; z < size; z++){
            Celula tmp = this.primeiro;
            Celula tmp2 = this.primeiro;
            k = 0;
            for (tmp = primeiro.prox; k < z && tmp.prox != null; k++, tmp = tmp.prox);
            if (tmp.prox != null){
                k = 0;
                for (tmp2 = primeiro.prox; k < z+1 && tmp2.prox != null; k++, tmp2 = tmp2.prox);
                if(compareDates(tmp.elemento.getDateTmp(), tmp2.elemento.getDateTmp()) == 0){
                    //MyIO.println("aqui2");
                    swap(z, z+1);
                }
            }

        }
    }

    private void quicksort(int inicio, int fim){
        if(inicio < fim){
            int p = partition(inicio, fim);
            quicksort(inicio, p-1);
            quicksort(p+1, fim);
        }
    }
    

    private int partition(int inicio, int fim){
        int k = 0;
        Celula Cesq = this.primeiro;
        Celula Cdir = this.primeiro;
        Celula pivot = this.primeiro;
        for (pivot = primeiro.prox; k < fim && pivot.prox != null; k++, pivot = pivot.prox);
        int i = inicio;
        for (int j = inicio; j < fim; j++){
            k=0;
            for (Cesq = primeiro.prox; k < i && Cesq.prox != null; k++, Cesq = Cesq.prox);
            k=0;
            for (Cdir = primeiro.prox; k < j && Cdir.prox != null; k++, Cdir = Cdir.prox);
            if(compareDates(Cdir.elemento.getDateTmp(), pivot.elemento.getDateTmp()) < 0){
                swap(i, j);
                i = i+1;
            }


        }
        swap(i, fim);
        return i;
    }
    

    /**
     * Algoritmo de ordenacao Quicksort.
    * @param int esq inicio do array a ser ordenado
    * @param int dir fim do array a ser ordenado
     */
    /*
    private void quicksort(int esq, int dir) {
        int i = esq, j = dir;

        int k = 0;
        Celula Cesq = this.primeiro;
        for (Cesq = primeiro.prox; k < i && Cesq.prox != null; k++, Cesq = Cesq.prox);
        //MyIO.println(Cesq.elemento.getDateTmp());

        k = 0;
        Celula Cdir = this.primeiro;
        for (Cdir = primeiro.prox; k < j && Cdir.prox != null; k++, Cdir = Cdir.prox);
        //MyIO.println(Cdir.elemento.getDateTmp());

        int meio = (int)Math.floor(((float)esq+(float)dir)/2);
        //MyIO.println("esq = " + esq);
        //MyIO.println("dir = " + dir);
        //MyIO.println("(esq+dir)/2 = " + (esq+dir)/2);
        //MyIO.println("meio = " + meio);
        k = 0;
        Celula pivo = this.primeiro;
        for (pivo = primeiro.prox; k < meio && pivo.prox != null; k++, pivo = pivo.prox);

        while (i <= j) {

            while (compareDates(pivo.elemento.getDateTmp(), Cesq.elemento.getDateTmp()) > 0) {
                i++;
                //MyIO.println("i " + i);
                k = 0;
                for (Cesq = primeiro.prox; k < i; k++, Cesq = Cesq.prox);
                //MyIO.println("primeiro");
            }
            while (compareDates(Cdir.elemento.getDateTmp(), pivo.elemento.getDateTmp()) == 1) {
                j--;
                //MyIO.println("j " + j);
                k = 0;
                for (Cdir = primeiro.prox; k < j; k++, Cdir = Cdir.prox);
                //MyIO.println("segundo");
            }
            if (i <= j) {
                //MyIO.println(i + " <= "+ j + (i <=j));
                //int tmp = i;
                //i = j;
                //j = tmp;
                swap(i, j);
                i++;
                j--;
            }
            //MyIO.println("a");
        }
        //MyIO.println("esq " + esq + " < j " + j + " " + (esq < j));
        if (esq < j)  quicksort(esq, j);
        //MyIO.println("i " +i+ " < dir "+dir + (i < dir));
        if (i < dir)  quicksort(i, dir);
    }
*/
    public void swap(int i, int j) {
        //MyIO.println(i+ " "+ j);
        //Game temp = array[i];
        int k = 0;
        Celula temp = this.primeiro;
        for (temp = primeiro.prox; k < i && temp.prox != null ; k++, temp = temp.prox);
        Game tempG = temp.elemento.clone();

        k = 0;
        Celula iC = this.primeiro;
        for (iC = primeiro.prox; k < i && iC.prox != null; k++, iC = iC.prox);
        Game iG = iC.elemento.clone();
        //MyIO.println(iC.elemento.getName());

        k = 0;
        Celula jC = this.primeiro;
        for (jC = primeiro.prox; k < j && jC.prox != null; k++, jC = jC.prox);
        Game jG = jC.elemento.clone();
        //MyIO.println(jC.elemento.getName());       

        //array[i] = array[j];
        
        iC.elemento = jG; 
        jC.elemento = iG;

        
        //MyIO.println("iC = " + iC.ant.prox.elemento.getName());
        //MyIO.println("jC = " + jC.ant.prox.elemento.getName());
        
        
        //movimentacoes += 3;
    }


    public int compareDates(String one, String two){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM/yyyy", Locale.ENGLISH);

        int resp = 0;

        try{
            Date date1 = sdf.parse(one); 
            //System.out.println("date1 " + date1);            
            Date date2 = sdf.parse(two);         
            //System.out.println("date2 " + date2);

            if(date1.equals(date2))   
            {  
                resp = 0;
            }   
            else if(date1.before(date2))   
            {  
                resp = -1; 
            }   
            else if(date1.after(date2))   
            {  
                resp = 1;  
            }  
        } catch (Exception eD){
            MyIO.println("Erro");
        }

        //MyIO.println(resp);
        return(resp);
    }
}

    /**
    * Procura um elemento e retorna se ele existe.
    * @param x int elemento a ser pesquisado.
    * @return <code>true</code> se o array existir,
    * <code>false</code> em caso contrario.
    */
/*   public boolean pesquisar(Game x) {
      boolean retorno = false;
      for (int i = 0; i < n && retorno == false; i++) {
         retorno = (array[i] == x.clone());
      }
      return retorno;
   }*/


class Game {
    private int app_id, age, dlcs, avg_pt;
    private String name, owners, website, developers, dateTmp, genre;
    private boolean windows, mac, linux;
    private float price, upvotes, downvotes;
    private String[] languages, genres;
    private java.util.Date date;


    //================ construtor do objeto game ================//
    //================           vazio           ================//
    public Game ()
    {
        //int
        this.app_id = 0;
        this.age = 0;
        this.dlcs = 0;
        this.avg_pt = 0;
        

        //string
        this.name = "";
        this.owners = "";
        this.website = "";
        this.developers = "";
        this.dateTmp = "";
        this.genre = "";

        //boolean
        this.windows = false;
        this.mac = false;
        this.linux = false;

        //float
        this.price = 0.0f;
        this.upvotes = 0.0f;
        this.downvotes = 0.0f;
        

        //String[]
        this.languages = new String[0];
        this.genres = new String[0];
/*
        //date
        this.date = new SimpleDateFormat("MMM/yyyy");    */ 
    }

    public Game (String app_id) {
        this.app_id = Integer.parseInt(app_id);
    }

    //================ construtor do objeto game ================//
    public Game (int app_id, int age, int dlcs, int avg_pt, float price, float upvotes,
			     String name, String owners, String website, String developers, String genre,
                 boolean windows, boolean mac, boolean linux,
			     String [] languages, String [] genres, String dateTmp)
	{
        //int
		this.app_id = app_id;
        this.age = age;
        this.dlcs = dlcs;
        this.avg_pt = avg_pt;

        //string
        this.name = name;
		this.owners = owners;
        this.website = website;
        this.developers = developers;

        //boolean
        this.windows = windows;
        this.mac = mac;
        this.linux = linux;

        //float
		this.price = price;
		this.upvotes = upvotes;

        //String[]
		this.languages = languages;
		this.genres = genres;

        //date
		this.dateTmp = dateTmp;		
	}


    //================ metodos parar pegar e ================//
    //================ receber modificacoes  ================//
    //================      do objeto        ================// 

    //================ ints ================//
    //=========================================get ints
    public int getApp_id () {
        return this.app_id;
    }

    public int getAge () {
        return this.age;
    }

    public int getDlcs () {
        return this.dlcs;
    }

    public int getAvg_pt () {
        return this.avg_pt;
    }

    //=========================================set ints

    public void setApp_id (int tmp) {
        this.app_id = tmp;
    }

    public void setAge (int tmp) {
        this.age = tmp;
    }

    public void setDlcs (int tmp) {
        this.dlcs = tmp;
    }
    
    public void setAvg_pt (int tmp) {
        this.avg_pt = tmp;
    }

    //================ Strings ================//
    //=========================================get Strings

    public String getName () {
        return this.name;
    }

    public String getOwners () {
        return this.owners;
    }

    public String getWebsite () {
        return this.website;
    }

    public String getDevelopers () {
        return this.developers;
    }

    public String getDateTmp () {
        return this.dateTmp;
    }

    public Date getDateTmpDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM/yyyy", Locale.ENGLISH);
        Date tmp = new Date();
        try{
            tmp = sdf.parse(this.getDateTmp());
        } catch (Exception e) {
            MyIO.println("");
        }
        return tmp;
    }

    //=========================================set Strings

    public void setName (String tmp) {
        this.name = tmp;
    }

    public void setOwners (String tmp) {
        this.owners = tmp;
    }

    public void setWebsite (String tmp) {
        this.website = tmp;
    }

    public void setDevelopers (String tmp) {
        this.developers = tmp;
    }

    public void setDateTmp (String tmp) {
        this.dateTmp = tmp;
    }

    //================ booleans ================//
    //=========================================get booleans

    public boolean getWindows () {
        return this.windows;
    }

    public boolean getMac () {
        return this.mac;
    }

    public boolean getLinux () {
        return this.linux;
    }

    //=========================================set booleans

    public void setWindows (boolean tmp) {
        this.windows = tmp;
    }

    public void setMac (boolean tmp) {
        this.mac = tmp;
    }

    public void setLinux (boolean tmp) {
        this.linux = tmp;
    }

    //================ float ================//
    //=========================================get float

    public float getPrice () {
        return this.price;
    }

    public float getUpvotes () {
        return this.upvotes;
    }

    //=========================================set float

    public void setPrice (float tmp) {
        this.price = tmp;
    }

    public void setUpvotes (float tmp) {
        this.upvotes = tmp;
    }

    //================ String vector ================//
    //=========================================get String vector

    public String[] getLanguages () {
        return this.languages;
    }

    public String[] getGenres () {
        return this.genres;
    }

    //=========================================set String vector

    public void setLanguages (String[] tmp) {
        this.languages = languages;
    }

    public void setGenres (String[] tmp) {
        this.genres = genres;
    }

    //================ date ================//
    //=========================================get date

    public java.util.Date getDate () {
        return this.date;
    }

    //=========================================set date

    public void setDate (java.util.Date tmp) {
        this.date = tmp;
    }

    //================ clone ================//
    public Game clone() {
        Game cloned = new Game();
        cloned.name = this.name;
        cloned.owners = this.owners;
        cloned.website = this.website;
        cloned.developers = this.developers;
        cloned.languages = this.languages;
        cloned.genre = this.genre;
        cloned.genres = this.genres;
        cloned.dateTmp = this.dateTmp;
        cloned.app_id = this.app_id;
        cloned.age = this.age;
        cloned.dlcs = this.dlcs;
        cloned.avg_pt = this.avg_pt;
        cloned.price = this.price;
        cloned.upvotes = this.upvotes;
        cloned.windows = this.windows;
        cloned.mac = this.mac;
        cloned.linux = this.linux;
        return cloned;
    }
    
    public Game clone (Game original) {
        return (new Game(original.app_id, original.age, original.dlcs, original.avg_pt,
                 original.price, original.upvotes,
			     original.name, original.owners, original.website, original.developers, original.genre,
                 original.windows, original.mac, original.linux,
			     original.languages,original.genres, original.dateTmp));
    }

    public String[] checkDate (String[] date1) {
        if (date1.length != 3) {
            String[] date = new String[date1.length + 1];
            String idk = date1[1];
            idk = " ," + idk;
            /*MyIO.println(idk);*/

            date[2] = idk;
            date[1] = date1[0];  
            return (date);         
        } else {
            String[] date = new String[date1.length];
            date = date1;
            return (date);
        }
    }



    //================ create from ================//
    //================     str     ================//
    public void createFromStr (String tmp) {
        this.app_id = 0;
        this.age = 0;
        this.dlcs = 0;
        this.avg_pt = 0;
        

        //string
        this.name = "";
        this.owners = "";
        this.website = "";
        this.developers = "";
        this.dateTmp = "";
        this.genre = "";

        //boolean
        this.windows = false;
        this.mac = false;
        this.linux = false;

        //float
        this.price = 0.0f;
        this.upvotes = 0.0f;
        this.downvotes = 0.0f;
        

        //String[]
        this.languages = new String[0];
        this.genres = new String[0];

        //                            //
    //-------ler e dividir a entrada-------//
        //                            //
        int control = 0;
        String str = "";
        char x;

        for (int j = 0; j < tmp.length(); j++) {
            if (tmp.charAt(j) == '\"'){
                control++;
            }
            if ((control % 2) == 0 && tmp.charAt(j) == ',') {
                str += "separatepls";
            } else {
                x = tmp.charAt(j);
                str += x;
            }

        }
        String[] teste = new String[0];
        teste = str.split("separatepls", 0);

        /*int h = 0;
        for (String a : teste) {
            MyIO.println("STR [" +h +"] = " + teste[h]);
            h++;
        }*/


        this.app_id = Integer.parseInt(teste[0]);
        this.name = teste[1];

        String dateTmpTmp = teste[2];
        dateTmpTmp = dateTmpTmp.replaceAll("\"", "");
        this.dateTmp = fazerData(dateTmpTmp);

        this.owners = teste[3];
        this.age = Integer.parseInt(teste[4]);
        this.price = Float.parseFloat(teste[5]);
        this.dlcs = Integer.parseInt(teste[6]);

        String languagesTmp = teste[7];
        languagesTmp = languagesTmp.replaceAll("\"", "");
        languagesTmp = languagesTmp.substring(0);
        int size = (languagesTmp.length() -1 );  
        languagesTmp = languagesTmp.substring(0, size);  
        languagesTmp = languagesTmp.replaceAll("'", "");
        this.languages = languagesTmp.split(",");

        this.website = teste[8];

        this.windows = Boolean.parseBoolean(teste[9]);
        this.mac = Boolean.parseBoolean(teste[10]);
        this.linux = Boolean.parseBoolean(teste[11]);

        this.upvotes = Float.parseFloat(teste[12]);
        this.downvotes = Float.parseFloat(teste[13]);
        this.upvotes = (this.upvotes/(this.upvotes + this.downvotes)*100);

        this.avg_pt = Integer.parseInt(teste[14]);

        this.developers = teste[15];
        this.developers.replaceAll("\"", "");

        String genresTmp = teste[teste.length - 1];
        if (genresTmp.charAt(0) == '"')
        {
            genresTmp = genresTmp.replaceAll("\"", "");       
            this.genres = genresTmp.split(",");
        } else {
            this.genre = genresTmp;
        }


    }

    /**
     * fazerData - formatar corretamente a data de lancamento 
     * @param (String) - a data separada pra ser formatada
     * @return (String) - a data na forma correta
     */

    public String fazerData(String inputData) {
        if (inputData.length() > 10) {
            String[] tmpData = inputData.split(",");
            String mes = "" + tmpData[0].charAt(0) + tmpData[0].charAt(1) + tmpData[0].charAt(2);
            String outputData = mes + "/" + tmpData[tmpData.length - 1].substring(1);
            return outputData;
        } else {
            String[] tmpData = inputData.split(" ");
            String outputData = tmpData[0] + "/" + tmpData[tmpData.length - 1];
            return outputData;
        }
    }

    /**
     * calcularHoras - formatar corretamente o play time
     * @return (String) - as o play time formatado
     */  

    public String calcularHoras() {
        if (this.avg_pt == 0) {
            return null;
        } else {
            int horas = Math.round((float) (this.avg_pt / 60));
            int minutos = Math.round(this.avg_pt % 60);
            String time;
            if (horas == 0) {
                time = Integer.toString(minutos) + "m";
            } else {
                time = (Integer.toString(horas) + "h " + Integer.toString(minutos) + "m");
            }
            return time;
        }
    }

    /**
     * createObjVar - create the obj based on the separated strings
     * @param (String) - the separated string of the games.csv
     */ 
/*
    public void createObjVar (String[] idName, String[] date, String[] ownAgePricDlc,
                              String[] languagesFinal, String[] webWinMacLinUpvtAvg,
                              String[] devGen, String[] gene) {
        DateFormat df = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
        DateFormat df2 = new SimpleDateFormat("MMM yyyy", Locale.US);
        //int
        this.app_id = Integer.parseInt(idName[0]);
        this.age = Integer.parseInt(ownAgePricDlc[2]);
        this.dlcs = Integer.parseInt(ownAgePricDlc[4]);
        this.avg_pt = Integer.parseInt(webWinMacLinUpvtAvg[7]);
        //MyIO.println("horas = " + this.avg_pt);

        //string
        this.name = idName[1];
        this.owners = ownAgePricDlc[1];
        this.website = webWinMacLinUpvtAvg[1];
        this.developers = devGen[0];

        //boolean
        this.windows = Boolean.parseBoolean(webWinMacLinUpvtAvg[2]);
        this.mac = Boolean.parseBoolean(webWinMacLinUpvtAvg[3]);
        this.linux = Boolean.parseBoolean(webWinMacLinUpvtAvg[4]);

        //float
        this.price = Float.parseFloat(ownAgePricDlc[3]);
        /*this.upvotes = 1 + (Math.round(100*((1 - (Float.parseFloat(webWinMacLinUpvtAvg[6])/
                        Float.parseFloat(webWinMacLinUpvtAvg[5]))))));*/
/*        this.upvotes = (Float.parseFloat(webWinMacLinUpvtAvg[5]) / (Float.parseFloat(webWinMacLinUpvtAvg[5])
                                                                 + Float.parseFloat(webWinMacLinUpvtAvg[6]))*100);


        //String[]
        this.languages = languagesFinal;
        this.genres = gene;

        //date
        this.dateTmp = date[1]; //date[1];
        dateTmp = fazerData(dateTmp);
        //MyIO.println("date = " + dateTmp);
        /*try {
            this.date = df.parse(dateTmp);
        } catch(ParseException e) {
            e.printStackTrace();
        }*/
        

        /*
        int aI = Integer.parseInt(idName[0]);
        String name = idName[1];
        String data = date[1];
        String own = ownAgePricDlc[1];
        int age = Integer.parseInt(ownAgePricDlc[2]);
        float pric = Float.parseFloat(ownAgePricDlc[3]);
        int dlc = Integer.parseInt(ownAgePricDlc[4]);
        String[] lang = languagesFinal;
        String webS = webWinMacLinUpvtAvg[1];
        boolean win = Boolean.parseBoolean(webWinMacLinUpvtAvg[2]);
        boolean mac = Boolean.parseBoolean(webWinMacLinUpvtAvg[3]);
        boolean lin = Boolean.parseBoolean(webWinMacLinUpvtAvg[4]);
        float upvt = 1 - (Float.parseFloat(webWinMacLinUpvtAvg[5])/
                        Float.parseFloat(webWinMacLinUpvtAvg[6]));
        int avg = Integer.parseInt(webWinMacLinUpvtAvg[7])/60;
        String dev = devGen[1];
        String[] gen = gene;

        Game(aI, age, dlc, avg, pric, upvt, name, own, webS,
             dev, win, mac, lin, lang, gen, new DateFormat());*/
    /*}*/

    //================ imprimir ================//
    public void printGame () {
        MyIO.print(app_id + " " + name + " " + dateTmp + " " + owners +
                     " " + age + " " + price + " " + dlcs + " ");
        
        for (int j = 0; j < languages.length; j++) {
            if (j < languages.length - 1){
                MyIO.print(languages[j] + ",");
            }
            else {MyIO.print(languages[j]);}
        }

        MyIO.print("] " + website + " " + windows + " " + mac +
         " " + linux + " " + (int)(Math.round(this.upvotes)) +"%" + " " + calcularHoras() + " " + developers + " " + "[");

        if (genres.length > 0) {
            for (int j = 0; j < genres.length; j++) {
                if (j < genres.length - 1){
                    MyIO.print(genres[j] + ", ");
                }
                else {MyIO.print(genres[j] + "]\n");}
            } 
        } else {
            MyIO.print(genre + "]\n");
        }
    }
}