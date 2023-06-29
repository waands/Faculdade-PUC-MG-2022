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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameTreeAVL {
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
        AVL gameList = new AVL();    
         
        while (!isFim(pubin)) {
            try {
                String line;
                objReader = new BufferedReader(new FileReader("/tmp/games.csv"));
                while ((line = objReader.readLine()) != null) {
                    String[] gameId = line.split(",", 0);
                    Game jojo = new Game(gameId[0]);
                    if (gameId[0].equals(pubin)) {
                        jojo.createFromStr(line);
                        gameList.inserir(jojo);
                        //jojo.printGame();
                        
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            pubin = MyIO.readLine();
        }

        int operacoes = Integer.parseInt(MyIO.readLine());
        

        
        for (int k = 0; k < operacoes; k++) {
            try{
                pubin = MyIO.readLine();    //ler o proximo comando da lista
                //MyIO.println(pubin);
                String input[] = pubin.split(" "); //separar nos espaços

                
                String line;
                Game tmp = new Game();
                objReader = new BufferedReader(new FileReader("/tmp/games.csv"));
                while ((line = objReader.readLine()) != null) {
                    String[] gameId = line.split(",", 3);
                    if (input.length == 2 && (gameId[0].equals(input[1]) ||
                                              (gameId[1].compareTo(input[1]) == 0))){
                        tmp.createFromStr(line);
                    }
    
                }
                
/*
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
*/              

                if (input[0].compareTo("I") == 0) {                     
                    gameList.inserir(tmp);
                }
                else if (input[0].compareTo("R") == 0) {             
                    gameList.remover(tmp);
                    //System.out.print("(R) " + tmp.getName() + "\n");
                }
                

            }catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        pubin = MyIO.readLine();

        while (!isFim(pubin)) {
            try {
                //pubin = MyIO.readLine();    //ler o proximo comando da lista
                //String input[] = pubin.split(" "); //separar nos espaços

                
                String line;
                Game tmp = new Game();
                objReader = new BufferedReader(new FileReader("/tmp/games.csv"));
                while ((line = objReader.readLine()) != null) {
                    String[] gameId = line.split(",", 3);    
                    //MyIO.println(gameId[1]);  
                    if ((gameId[1].compareTo(pubin) == 0)){  
                              
                        tmp.createFromStr(line);
                    }                      
                    /*else if (input.length == 3 && gameId[0].equals(input[2])){
                        tmp.createFromStr(line);
                    }*/
                }
                MyIO.println(pubin);                
                
                //MyIO.println(tmp.getName());
                        MyIO.print("=>");
                        MyIO.print("raiz ");
                        if (gameList.pesquisar(tmp)){
                            MyIO.print("SIM\n");
                        } else {
                            MyIO.print("NAO\n");
                        } 
/*
                MyIO.println(pubin);
                MyIO.println("=>");
                if (gameList.pesquisar(pubin)){
                    MyIO.print("SIM");
                } else {
                    MyIO.print("NAO");
                }
*/

            } catch (Exception e7) {
                e7.printStackTrace();
            }
            pubin = MyIO.readLine();
        }

        




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
            FileWriter fw = new FileWriter("783518_avl.txt");
            fw.write("783518\t" + tempoTotal/1000000 + "ms\t" + comparacoes);
            fw.close();
        } catch (IOException e){
            MyIO.println("");
        }           
        
    }
}

class No {
    public Game elemento; // Conteudo do no.
    public No esq, dir; // Filhos da esq e dir.
    public int nivel; // Numero de niveis abaixo do no

    /**
     * Construtor da classe
     * @param elemento Conteudo do no.
     */
    public No(Game elemento) {
        this(elemento, null, null, 1);
    }

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     * @param esq      No da esquerda.
     * @param dir      No da direita.
     */
    public No(Game elemento, No esq, No dir, int nivel) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.nivel = nivel;
    }

    /**
     * Cálculo do número de níveis a partir de um vértice
     */
    public void setNivel() {
        this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
    }

    /**
     * Retorna o número de níveis a partir de um vértice
     * @param no nó que se deseja o nível.
     */
    public static int getNivel(No no) {
        return (no == null) ? 0 : no.nivel;
    }
}




class AVL {
    public No raiz; // Raiz da arvore.

    /**
     * Construtor da classe.
     */
    public AVL() {
        raiz = null;
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     * @param x Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir, <code>false</code> em caso
     *         contrario.
     */
    public boolean pesquisar(Game x) {
        return pesquisar(x.getName(), raiz);
    }

    /**
     * Metodo privado recursivo para pesquisar elemento.
     * @param x Elemento que sera procurado.
     * @param i No em analise.
     * @return <code>true</code> se o elemento existir, <code>false</code> em caso
     *         contrario.
     */
    private boolean pesquisar(String x, No i) {
      boolean resp;
        if (i == null) {
         resp = false;

      } else if (x.compareTo(i.elemento.getName()) == 0) {
         resp = true;
         //MyIO.print("raiz ");

      } else if (i.elemento.getName().compareTo(x) > 0) {
         MyIO.print("esq ");
         resp = pesquisar(x, i.esq);

      } else {
         MyIO.print("dir ");
         resp = pesquisar(x, i.dir);
      }
      return resp;
    }

    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharCentral() {
        System.out.print("[ ");
        caminharCentral(raiz);
        System.out.println("]");
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * @param i No em analise.
     */
    private void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.esq); // Elementos da esquerda.
            System.out.print(i.elemento.getName() + " "); // Conteudo do no.
            caminharCentral(i.dir); // Elementos da direita.
        }
    }

    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharPre() {
        System.out.print("[ ");
        caminharPre(raiz);
        System.out.println("]");
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * @param i No em analise.
     */
    private void caminharPre(No i) {
        if (i != null) {
            System.out.print(i.elemento.getName() + "(fator " + (No.getNivel(i.dir) - No.getNivel(i.esq)) + ") "); // Conteudo do no.
            caminharPre(i.esq); // Elementos da esquerda.
            caminharPre(i.dir); // Elementos da direita.
        }
    }

    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharPos() {
        System.out.print("[ ");
        caminharPos(raiz);
        System.out.println("]");
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * @param i No em analise.
     */
    private void caminharPos(No i) {
        if (i != null) {
            caminharPos(i.esq); // Elementos da esquerda.
            caminharPos(i.dir); // Elementos da direita.
            System.out.print(i.elemento.getName() + " "); // Conteudo do no.
        }
    }

    /**
     * Metodo publico iterativo para inserir elemento.
     * @param x Elemento a ser inserido.
     * @throws Exception Se o elemento existir.
     */
    public void inserir(Game x) throws Exception {
        raiz = inserir(x, raiz);
    }

    /**
     * Metodo privado recursivo para inserir elemento.
     * @param x Elemento a ser inserido.
     * @param i No em analise.
     * @return No em analise, alterado ou nao.
     * @throws Exception Se o elemento existir.
     */
    private No inserir(Game x, No i) throws Exception {
        
      if (i == null) {
         i = new No(x);
      } else if (i.elemento.getName().compareTo(x.getName())> 0) {
         i.esq = inserir(x, i.esq);

      } else if (x.getName().compareTo(i.elemento.getName())> 0) {
         i.dir = inserir(x, i.dir);

      } else {
        //MyIO.println(i.elemento.getName());
         throw new Exception("Erro ao inserir!");
      }

        return i;
    }

    /**
     * Metodo publico iterativo para remover elemento.
     * @param x Elemento a ser removido.
     * @throws Exception Se nao encontrar elemento.
     */
    public void remover(Game x) throws Exception {
        raiz = remover(x.getName(), raiz);
    }

    /**
     * Metodo privado recursivo para remover elemento. 
     * @param x Elemento a ser removido.
     * @param i No em analise.
     * @return No em analise, alterado ou nao.
     * @throws Exception Se nao encontrar elemento.
     */

    private No remover(String x, No i) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao remover!");
        } else if (x.compareTo(i.elemento.getName()) < 0) {
            i.esq = remover(x, i.esq);
        } else if (x.compareTo(i.elemento.getName()) > 0) {
            i.dir = remover(x, i.dir);
        // Sem no a direita.
        } else if (i.dir == null) {
            i = i.esq;
        // Sem no a esquerda.
        } else if (i.esq == null) {
            i = i.dir;
        // No a esquerda e no a direita.
        } else {
            i.esq = maiorEsq(i, i.esq);
        }
        return balancear(i);
    }

    /**
     * Metodo para trocar o elemento "removido" pelo maior da esquerda.
     * @param i No que teve o elemento removido.
     * @param j No da subarvore esquerda.
     * @return No em analise, alterado ou nao.
     */
    private No maiorEsq(No i, No j) {
        // Encontrou o maximo da subarvore esquerda.
        if (j.dir == null) {
            i.elemento = j.elemento.clone(); // Substitui i por j.
            j = j.esq; // Substitui j por j.ESQ.
        // Existe no a direita.
        } else {
            // Caminha para direita.
            j.dir = maiorEsq(i, j.dir);
        }
        return j;
    }

    private No balancear(No no) throws Exception {
        if (no != null) {
            int fator = No.getNivel(no.dir) - No.getNivel(no.esq);
            // Se balanceada
            if (Math.abs(fator) <= 1) {
                no.setNivel();
            // Se desbalanceada para a direita
            } else if (fator == 2) {
                int fatorFilhoDir = No.getNivel(no.dir.dir) - No.getNivel(no.dir.esq);
                // Se o filho a direita tambem estiver desbalanceado
                if (fatorFilhoDir == -1) {
                    no.dir = rotacionarDir(no.dir);
                }
                no = rotacionarEsq(no);
            // Se desbalanceada para a esquerda
            } else if (fator == -2) {
                int fatorFilhoEsq = No.getNivel(no.esq.dir) - No.getNivel(no.esq.esq);
                // Se o filho a esquerda tambem estiver desbalanceado
                if (fatorFilhoEsq == 1) {
                    no.esq = rotacionarEsq(no.esq);
                }
                no = rotacionarDir(no);
            } else {
                throw new Exception(
                        "Erro no No(" + no.elemento + ") com fator de balanceamento (" + fator + ") invalido!");
            }
        }
        return no;
    }

    private No rotacionarDir(No no) {
        //System.out.println("Rotacionar DIR(" + no.elemento + ")");
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;
        no.setNivel(); // Atualizar o nivel do no
        noEsq.setNivel(); // Atualizar o nivel do noEsq

        return noEsq;
    }

    private No rotacionarEsq(No no) {
        //System.out.println("Rotacionar ESQ(" + no.elemento + ")");
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;

        no.setNivel(); // Atualizar o nivel do no
        noDir.setNivel(); // Atualizar o nivel do noDir
        return noDir;
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