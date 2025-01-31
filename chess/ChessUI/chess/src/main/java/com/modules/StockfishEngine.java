package com.modules;

import java.io.*;

public class StockfishEngine {

   
   int cP1,cP2,tP1,tP2;
   // Play play  = new Play();
    private Process stockfishProcess;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String stockmove;  
    public boolean start() {
        try {
            stockfishProcess = new ProcessBuilder("/opt/homebrew/bin/stockfish").start();
            reader = new BufferedReader(new InputStreamReader(stockfishProcess.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(stockfishProcess.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void stop() {
        try {
            writer.write("quit\n");
            writer.flush();
            stockfishProcess.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  String getBestMove(String fen) {
        try {
           
            writer.write("uci\n");
            writer.write("isready\n");
            writer.write("position fen " + fen + "\n");
            writer.write("go movetime 10\n");

            writer.flush();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("bestmove")) {
                    return line.split(" ")[1];
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public void targetmove(String stockmove){
        
        String currentpos=stockmove.substring(0, 2);
        String  targetmove=stockmove.substring(2);
        
        System.out.println("the current position :"+currentpos);
        System.out.println("the target position :"+targetmove);

        getPosition(currentpos,targetmove);
        System.out.println(cP1+" "+cP2+ "\n" +tP1+" "+tP2);
       // System.out.println("hello");
        
       // System.out.println(cP1+" "+cP2+"\n"+tP1+" "+tP2);
        PlayWithComp.c1 = cP1;
        PlayWithComp.c2 = cP2;
        PlayWithComp.t1 = tP1;
        PlayWithComp.t2 = tP2;
    } 

    
    public void getPosition(String currentPos,String targetPos){
        int row = 8;
        String str;
        for(int i=0;i<8;i++){
            char column = 'a';
            for(int j=0;j<8;j++){
                str = "";
                str = str+column+row;
                if(str.contains(currentPos)){
                    cP1= i;
                    cP2 = j;
            
                }
                else if(str.contains(targetPos)){
                    tP1 = i;
                    tP2 = j;
                
                }
                column++;
            }
            row--;
        }
    }
    public  void generateFEN(Board[][] board) {
        StringBuilder fen = new StringBuilder();
         Pieces p1= new Pieces();
        // Piece positions
        for (int i = 0; i < 8; i++) {
            int emptysquare = 0;
            for (int j = 0; j < 8; j++) {
              //  Board piece = board[i][j];
                if (board[i][j].isBlank ) {
                    emptysquare++;
                } else {
                    if (emptysquare > 0) {
                        fen.append(emptysquare);
                        emptysquare = 0;
                    }
                    fen.append(board[i][j].id);
                }
            }
            if (emptysquare > 0) {
                fen.append(emptysquare);
            }
            if (i < 7) {
                fen.append("/");
            }
        }

         fen.append(" b ");
        fen.append("-");
        fen.append(" -");
         fen.append(" 0 1");
       //  return fen.toString();
         startproces(fen.toString());

    }

    
    public void startproces(String fen){
        
          
       
            StockfishEngine stockfish = new StockfishEngine();
             
            System.out.println("FEN: " + fen);
            stockfish.start();
            String stockmove=stockfish.getBestMove(fen);
            stockfish.targetmove(stockmove);
        
        }

      /*   public void setter(int cP1,int cP2,int tP1,int tP2){
            play.cP1=cP1;
            play.cP2=cP2;
            play.tP1=tP1;
            play.tP2=tP2;
        }
            */
    }



    

