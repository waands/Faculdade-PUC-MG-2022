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

//static SimpleDateFormat default_dateFormat = new SimpleDateFormat("MMM/yyyy", Locale.ENGLISH);

/**
        // ---------------------------------- //
        // Find release date
        if (line.charAt(atr_index) != ',') {
            SimpleDateFormat df;
            if (line.charAt(atr_index) == '\"') {
                df = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
                atr_index++;
                c_search = '\"';
            } else {
                df = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
                c_search = ',';
            }
            while (true) {
                index++;
                if (line.charAt(index) == c_search) {
                    try {
                        this.release_date = df.parse(line.substring(atr_index, index));
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }
                    if (c_search == ',')
                        index++;
                    else if (c_search == '\"')
                        index += 2;
                    atr_index = index;
                    break;
                }
            }
        } else

 */
public class GameSelec01 {
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

        BufferedReader objReader = null;
        String pubin = MyIO.readLine();

        Lista gameList = new Lista(5000);     
        //Selecao gameSort = new Selecao(5000);     

         
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
            FileWriter fw = new FileWriter("matrícula_selecao.txt");
            fw.write("783518\t" + tempoTotal/1000000 + "ms\t" + gameList.comparacoes);
            fw.close();
        } catch (IOException e){
            MyIO.println("");
        }           
    }
}

class Lista{
   protected Game[] array;
   protected int n;
   public int comparacoes;


   /**
    * Construtor da classe.
    */
   public Lista () {
      this(0);
   }


   /**
    * Construtor da classe.
    * @param tamanho Tamanho da lista.
    */
   public Lista (int tamanho){
      array = new Game[tamanho];
      n = 0;

   }


   /**
    * Insere um elemento na primeira posicao da lista e move os demais
    * elementos para o fim da lista.
    * @param x int elemento a ser inserido.
    * @throws Exception Se a lista estiver cheia.
    */
   public void inserirInicio(Game x) throws Exception {
      //validar insercao
      if(n >= array.length){
         throw new Exception("Erro ao inserir!");
      } 

      //levar elementos para o fim do array
      for(int i = n; i > 0; i--){
         array[i] = array[i-1].clone();
      }

      array[0] = x.clone();
      n++;
   }


   /**
    * Insere um elemento na ultima posicao da lista.
    * @param x int elemento a ser inserido.
    * @throws Exception Se a lista estiver cheia.
    */
   public void inserirFim(Game x) throws Exception {

      //validar insercao
      if(n >= array.length){
         throw new Exception("Erro ao inserir!");
      }

      array[n] = x.clone();
      n++;
   }


   /**
    * Insere um elemento em uma posicao especifica e move os demais
    * elementos para o fim da lista.
    * @param x int elemento a ser inserido.
    * @param pos Posicao de insercao.
    * @throws Exception Se a lista estiver cheia ou a posicao invalida.
    */
   public void inserir(Game x, int pos) throws Exception {

      //validar insercao
      if(n >= array.length || pos < 0 || pos > n){
            MyIO.println(pos);
            MyIO.println(this.n);
         throw new Exception("Erro ao inserir!");
      }

      //levar elementos para o fim do array
      for(int i = n; i > pos; i--){
         array[i] = array[i-1];
      }

      array[pos] = x.clone();
      n++;
   }


   /**
    * Remove um elemento da primeira posicao da lista e movimenta 
    * os demais elementos para o inicio da mesma.
    * @return resp int elemento a ser removido.
    * @throws Exception Se a lista estiver vazia.
    */
   public Game removerInicio() throws Exception {

      //validar remocao
      if (n == 0) {
         throw new Exception("Erro ao remover!");
      }

      Game resp = new Game();
      resp = resp.clone(array[0]);
      n--;

      for(int i = 0; i < n; i++){
         array[i] = array[i+1];
      }

      return resp;
   }


   /**
    * Remove um elemento da ultima posicao da lista.
    * @return resp int elemento a ser removido.
    * @throws Exception Se a lista estiver vazia.
    */
   public Game removerFim() throws Exception {

      //validar remocao
      if (n == 0) {
         throw new Exception("Erro ao remover!");
      }

      return array[--n];
   }


   /**
    * Remove um elemento de uma posicao especifica da lista e 
    * movimenta os demais elementos para o inicio da mesma.
    * @param pos Posicao de remocao.
    * @return resp int elemento a ser removido.
    * @throws Exception Se a lista estiver vazia ou a posicao for invalida.
    */
   public Game remover(int pos) throws Exception {

      //validar remocao
      if (n == 0 || pos < 0 || pos >= n) {
         throw new Exception("Erro ao remover!");
      }

      Game resp = array[pos];
      n--;

      for(int i = pos; i < n; i++){
         array[i] = array[i+1];
      }

      return resp;
   }


   /**
    * Mostra os elementos da lista separados por espacos.
    */
   public void mostrar (){
      System.out.print("[ ");
      for(int i = 0; i < n; i++){
         array[i].printGame();
         MyIO.print(" ");
      }
      System.out.println("]");
   }


   /**
    * Procura um elemento e retorna se ele existe.
    * @param x int elemento a ser pesquisado.
    * @return <code>true</code> se o array existir,
    * <code>false</code> em caso contrario.
    */
   public boolean pesquisar(Game x) {
      boolean retorno = false;
      for (int i = 0; i < n && retorno == false; i++) {
         retorno = (array[i] == x.clone());
      }
      return retorno;
   }

   public void imprimirLista(){
        for (int i = 0; i < n; i++)
        {
            array[i].printGame();
        }
    }

/*
    public void sort() {
        MyIO.println("pai");
    }*/

    public void sort() {
    //o metodo sort compara cada elemento do array com o resto do array e
    //pega o menor elemento pra fazer um swap com a posicao que ele
    //comecou comparando (achoq que e' isso, posso ta errado)
      for (int i = 0; i < (n - 1); i++) {
         int menor = i;
         comparacoes++;
         for (int j = (i + 1); j < n; j++){
            comparacoes++;
            //comparar as strings
            if ((array[menor].getName()).compareTo(array[j].getName()) > 0){
               menor = j;
            }
            comparacoes++;
         }
         //fazer swap
         swap(menor, i); 
      }
   }

   public void swap(int i, int j) {
        Game temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        movimentacoes += 3;
    }
}

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
        this.price = 0.00f;
        this.upvotes = 0.00f;
        this.downvotes = 0.00f;
        

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