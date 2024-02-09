package com.example.yatzygui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.*;

public class Controller {

    public Label val1;
    public Label val2;
    public Label val3;
    public Label val4;
    public Label val5;

    public CheckBox checkbx1;
    public CheckBox checkbx2;
    public CheckBox checkbx3;
    public CheckBox checkbx4;
    public CheckBox checkbx5;

    public List<Label> vals;
    public List<CheckBox> checkbxList;
    public List<Button> btnList;

    public Button ettorBtn;
    public Label curPlayer;

    public Label resultLabel1;
    public Label resultLabel2;
    public Button TvoBtn;

    int[] ettorP = {0, 0};
    int[] tvoorP = {0, 0};

    int[] treorP = {0, 0};
    int[] fyrorP = {0, 0};
    int[] femmorP = {0, 0};
    int[] sexorP = {0, 0};
    int[] ettparP = {0, 0};
    int[] tvoparP = {0, 0};
    int[] trissP = {0, 0};
    int[] fyrtalP = {0, 0};
    int[] lstegeP = {0, 0};
    int[] sstegeP = {0, 0};
    int[] kakP = {0, 0};

    int[] mYatzyP = {0, 0};
    int[] chansP = {0, 0};

    int[][] allaP = {ettorP, tvoorP, treorP, fyrorP, femmorP, sexorP, ettparP, tvoparP, trissP, fyrtalP, lstegeP, sstegeP, kakP, chansP, mYatzyP};

    boolean player1 = true;
    boolean[] plays1 = new boolean[15];
    boolean[] plays2 = new boolean[15];
    int rerolls = 3;
    public Button rollBtn;

    public void getResult() {
        int result = 0;
        if(player1) {
            for (int i = 0; i < 15; i++) {
                result = result + allaP[i][0];
            }
            resultLabel1.setText("fPlayer score: " + result);
        } else {
            for (int i = 0; i < 15; i++) {
                result = result + allaP[i][1];
            }
            resultLabel2.setText("sPlayer score: " + result);
        }

    }

    public void nulirane() {
        for (int i = 0; i < 5; i++) {
            vals.get(i).setText("0");
            checkbxList.get(i).setSelected(false);
        }
        rerolls = 3;
        rollBtn.setDisable(false);
        rollBtn.setText("ROLL - " + rerolls);
    }

    public void checkBtns() {
        if(player1) {
            for (int i = 0; i < 15; i++) {
                if(plays1[i]) {
                    btnList.get(i).setDisable(true);
                } else {
                    btnList.get(i).setDisable(false);
                }
            }
        } else {
            for (int i = 0; i < 15; i++) {
                if(plays2[i]) {
                    btnList.get(i).setDisable(true);
                } else {
                    btnList.get(i).setDisable(false);
                }
            }
        }
    }

    public boolean areAllTrue(boolean[] array)
    {
        for(boolean b : array) if(!b) return false;
        return true;
    }

    public void endGame() {

        if (areAllTrue(plays1) && areAllTrue(plays2)) {
            Stage endStage = new Stage();
            endStage.initModality(Modality.WINDOW_MODAL);
            Text vinnare = new Text("Spelare 1 VINNER!");
            vinnare.setStyle("-fx-font: 24 arial");
            Button quitBtn = new Button("QUIT");
            quitBtn.setMinWidth(120);
            VBox vbox = new VBox(vinnare, quitBtn);
            vbox.setAlignment(Pos.CENTER);
            vbox.setPadding(new Insets(10, 50, 50, 50));
            vbox.setSpacing(10);
            endStage.setResizable(false);
            endStage.setScene(new Scene(vbox, 400, 200));

            int result1 = 0;
            int result2 = 0;
            for (int i = 0; i < allaP.length; i++) {
                result1 += allaP[i][0];
                result2 += allaP[i][1];
            }

            for (int j = 0; j < 5; j++) {
                int x1 = 0;
                int x2 = 0;
                x1 += allaP[j][0];
                x2 += allaP[j][1];
                if (x1 < 50) {
                    result1 += 50;
                }
                if (x2 < 50) {
                    result2 += 50;
                }
            }

            if (result1 > result2) {
                vinnare.setText("Spelare 1 VINNER!");
            } else if (result1 < result2){
                vinnare.setText("Spelare 2 VINNER!");
            } else {
                vinnare.setText("INGEN VINNER, DRAW!");
            }
            endStage.show();
        }
    }

    public void afterRound(int numberBol, int point) {

        if(player1){
            curPlayer.setText("Current Player: " + 2);
            System.out.println("player1 - " + point + "points" + " [" + btnList.get(numberBol).getText() + "]" );
        } else{
            curPlayer.setText("Current Player: " + 1);
            System.out.println("player2 - " + point + "points" + " [" + btnList.get(numberBol).getText() + "]" );
        }
        nulirane();
        if(player1) {
            plays1[numberBol] = true;
        } else {
            plays2[numberBol] = true;
        }
        getResult();
        player1 = !player1;
        checkBtns();
        endGame();
    }

    public void ettor() {

        if(player1) {
                for(int i = 0; i < 5; i++) {
                    if(vals.get(i).getText().equals("1")) {
                        ettorP[0]++;

                    }
                }
                afterRound( 0 , ettorP[0]);
        } else {

                for (int i = 0; i < 5; i++) {
                    if (vals.get(i).getText().equals("1")) {
                        ettorP[1]++;

                    }
                }
                afterRound( 0 , ettorP[1]);
        }
    }

    public void tvoor() {
        if(player1) {
            for(int i = 0; i < 5; i++) {
                if(vals.get(i).getText().equals("2")) {
                    tvoorP[0] += 2;
                }
            }
            afterRound( 1 , tvoorP[0]);
        } else {
            for(int i = 0; i < 5; i++) {
                if(vals.get(i).getText().equals("2")) {
                    tvoorP[1] += 2;
                }
            }
            afterRound( 1 , tvoorP[1]);
        }
    }

    public void treor() {
        if(player1) {
            for(int i = 0; i < 5; i++) {
                if(vals.get(i).getText().equals("3")) {
                    treorP[0] += 3;
                }
            }
            afterRound( 2 , treorP[0]);
        } else {
            for(int i = 0; i < 5; i++) {
                if(vals.get(i).getText().equals("3")) {
                    treorP[1] += 3;
                }
            }
            afterRound( 2 , treorP[1]);
        }
    }

    public void fyror() {
        if(player1) {
            for(int i = 0; i < 5; i++) {
                if(vals.get(i).getText().equals("4")) {
                    fyrorP[0] += 4;
                }
            }
            afterRound( 3 , fyrorP[0]);
        } else {
            for(int i = 0; i < 5; i++) {
                if(vals.get(i).getText().equals("4")) {
                    fyrorP[1] += 4;
                }
            }
            afterRound( 3 , fyrorP[1]);
        }
    }

    public void femmor() {
        if (player1) {
            for(int i = 0; i < 5; i++) {
                if(vals.get(i).getText().equals("5")) {
                    femmorP[0] += 5;
                }
            }
            afterRound( 4 , femmorP[0]);
        } else {
            for(int i = 0; i < 5; i++) {
                if(vals.get(i).getText().equals("5")) {
                    femmorP[1] += 5;
                }
            }
            afterRound( 4 , femmorP[1]);
        }
    }

    public void sexor() {
        if (player1) {
            for(int i = 0; i < 5; i++) {
                if(vals.get(i).getText().equals("6")) {
                    sexorP[0] += 6;
                }
            }
            afterRound( 5 , sexorP[0]);
        } else {
            for(int i = 0; i < 5; i++) {
                if(vals.get(i).getText().equals("6")) {
                    sexorP[1] += 6;
                }
            }
            afterRound( 5 , sexorP[1]);
        }
    }

    public void ettPar() {
        ArrayList<Integer> sVals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sVals.add(Integer.valueOf(vals.get(i).getText()));
        }

        Collections.sort(sVals, Collections.reverseOrder());
        if(player1) {
            if (sVals.get(0) == sVals.get(1)) {
                ettparP[0] += sVals.get(0) + sVals.get(1);
            } else if (sVals.get(1) == sVals.get(2)) {
                ettparP[0] += sVals.get(1) + sVals.get(2);
            } else if (sVals.get(2) == sVals.get(3)) {
                ettparP[0] += sVals.get(2) + sVals.get(3);
            } else if (sVals.get(3) == sVals.get(4)) {
                ettparP[0] += sVals.get(3) + sVals.get(4);
            }
            afterRound( 6 , ettparP[0]);
        } else {
            if (sVals.get(0) == sVals.get(1)) {
                ettparP[1] += sVals.get(0) + sVals.get(1);
            } else if (sVals.get(1) == sVals.get(2)) {
                ettparP[1] += sVals.get(1) + sVals.get(2);
            } else if (sVals.get(2) == sVals.get(3)) {
                ettparP[1] += sVals.get(2) + sVals.get(3);
            } else if (sVals.get(3) == sVals.get(4)) {
                ettparP[1] += sVals.get(3) + sVals.get(4);
            }
            afterRound( 6 , ettparP[1]);
        }
    }


    public void tvoPar(){
        ArrayList<Integer> sVals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sVals.add(Integer.valueOf(vals.get(i).getText()));
        }

        Collections.sort(sVals);
        if (player1) {
            if(sVals.get(0) == sVals.get(1) && sVals.get(2) == sVals.get(3)) {
                tvoparP[0] += sVals.get(0) + sVals.get(1) + sVals.get(2) + sVals.get(3);
            } else if (sVals.get(0) == sVals.get(1) && sVals.get(3) == sVals.get(4)) {
                tvoparP[0] += sVals.get(0) + sVals.get(1) + sVals.get(3) + sVals.get(4);
            }
            else if (sVals.get(1) == sVals.get(2) && sVals.get(3) == sVals.get(4)) {
                tvoparP[0] += sVals.get(1) + sVals.get(2) + sVals.get(3) + sVals.get(4);
            }
            afterRound( 7 , tvoparP[0]);
        } else {
            if(sVals.get(0) == sVals.get(1) && sVals.get(2) == sVals.get(3)) {
                tvoparP[1] += sVals.get(0) + sVals.get(1) + sVals.get(2) + sVals.get(3);
            } else if (sVals.get(0) == sVals.get(1) && sVals.get(3) == sVals.get(4)) {
                tvoparP[1] += sVals.get(0) + sVals.get(1) + sVals.get(3) + sVals.get(4);
            }
            else if (sVals.get(1) == sVals.get(2) && sVals.get(3) == sVals.get(4)) {
                tvoparP[1] += sVals.get(1) + sVals.get(2) + sVals.get(3) + sVals.get(4);
            }
            afterRound( 7 , tvoparP[1]);
        }

    }

    public void triss() {
        ArrayList<Integer> sVals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sVals.add(Integer.valueOf(vals.get(i).getText()));
        }

        Collections.sort(sVals);
        if (player1) {
            if (sVals.get(0) == sVals.get(1) && sVals.get(1) == sVals.get(2)) {
                trissP[0] += sVals.get(1)*3;
            } else if (sVals.get(1) == sVals.get(2) && sVals.get(2) == sVals.get(3)) {
                trissP[0] += sVals.get(2)*3;
            } else if (sVals.get(2) == sVals.get(3) && sVals.get(3) == sVals.get(4)) {
                trissP[0] += sVals.get(3)*3;
            }
            afterRound( 8 , trissP[0]);
        } else {
            if (sVals.get(0) == sVals.get(1) && sVals.get(1) == sVals.get(2)) {
                trissP[1] += sVals.get(1)*3;
            } else if (sVals.get(1) == sVals.get(2) && sVals.get(2) == sVals.get(3)) {
                trissP[1] += sVals.get(2)*3;
            }  else if (sVals.get(2) == sVals.get(3) && sVals.get(3) == sVals.get(4)) {
                trissP[1] += sVals.get(3)*3;
            }
            afterRound( 8 , trissP[1]);
        }
    }

    public void fyrtal() {
        ArrayList<Integer> sVals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sVals.add(Integer.valueOf(vals.get(i).getText()));
        }

        Collections.sort(sVals);
        System.out.println(sVals);
        if (player1) {
            if (sVals.get(0) == sVals.get(1) && sVals.get(1) == sVals.get(2) && sVals.get(2) == sVals.get(3)){
                fyrtalP[0] += sVals.get(1)*4;
            } else if (sVals.get(1) == sVals.get(2) && sVals.get(2) == sVals.get(3) && sVals.get(3) == sVals.get(4)) {
                fyrtalP[0] += sVals.get(1)*4;
            }
            afterRound( 9 , fyrtalP[0]);
        } else {
            if (sVals.get(0) == sVals.get(1) && sVals.get(1) == sVals.get(2) && sVals.get(2) == sVals.get(3)){
                fyrtalP[1] += sVals.get(1)*4;
            } else if (sVals.get(1) == sVals.get(2) && sVals.get(2) == sVals.get(3) && sVals.get(3) == sVals.get(4)) {
                fyrtalP[1] += sVals.get(1)*4;
            }
            afterRound( 9 , fyrtalP[1]);
        }
    }

    public void lStege() {
        ArrayList<Integer> sVals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sVals.add(Integer.valueOf(vals.get(i).getText()));
        }

        Collections.sort(sVals);
        if (player1){
            if (sVals.get(0) < sVals.get(1)) {
                if (sVals.get(1) < sVals.get(2)) {
                    if (sVals.get(2) < sVals.get(3)) {
                        lstegeP[0] += 15;
                    }
                }
            }
            afterRound( 10 , lstegeP[0]);
        } else {
            if (sVals.get(0) < sVals.get(1)) {
                if (sVals.get(1) < sVals.get(2)) {
                    if (sVals.get(2) < sVals.get(3)) {
                        lstegeP[1] += 15;
                    }
                }
            }
            afterRound( 10 , lstegeP[1]);
        }
    }

    public void sStege() {
        ArrayList<Integer> sVals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sVals.add(Integer.valueOf(vals.get(i).getText()));
        }

        Collections.sort(sVals);
        if (player1) {
            if (sVals.get(1) < sVals.get(2)) {
                if (sVals.get(2) < sVals.get(3)) {
                    if (sVals.get(3) < sVals.get(4)) {
                        sstegeP[0] += 20;
                    }
                }

            }
            afterRound(11, sstegeP[0]);
        }
            else {
                if (sVals.get(1) < sVals.get(2)) {
                    if (sVals.get(2) < sVals.get(3)) {
                        if (sVals.get(3) < sVals.get(4)) {
                            sstegeP[1] += 20;
                        }
                    }
                }
                afterRound(11, sstegeP[1]);
            }
        }



    public void kok() {
        ArrayList<Integer> sVals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sVals.add(Integer.valueOf(vals.get(i).getText()));
        }

        Collections.sort(sVals);
        if (player1){
            if(sVals.get(0) == sVals.get(1) && sVals.get(1) == sVals.get(2)) {
                if (sVals.get(3) == sVals.get(4)) {
                    kakP[0] += sVals.get(0)*3 + sVals.get(3)*2;
                }
                afterRound(12, kakP[0]);
            } else {
                if (sVals.get(0) == sVals.get(1)) {
                    if (sVals.get(2) == sVals.get(3) && sVals.get(3) == sVals.get(4)) {
                        kakP[0] += sVals.get(0)*2 + sVals.get(3)*3;
                    }
                }
                afterRound(12, kakP[0]);
            }
        }else {
            if(sVals.get(0) == sVals.get(1) && sVals.get(1) == sVals.get(2)) {
                if (sVals.get(3) == sVals.get(4)) {
                    kakP[1] += sVals.get(0)*3 + sVals.get(3)*2;
                }
                afterRound(12, kakP[1]);
            } else {
                if (sVals.get(0) == sVals.get(1)) {
                    if (sVals.get(2) == sVals.get(3) && sVals.get(3) == sVals.get(4)) {
                        kakP[1] += sVals.get(0)*2 + sVals.get(3)*3;
                    }
                }
                afterRound(12, kakP[1]);
            }
        }
    }

    public void chans() {
        ArrayList<Integer> sVals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sVals.add(Integer.valueOf(vals.get(i).getText()));
        }

        Collections.sort(sVals);
        if (player1){
            chansP[0] += sVals.stream().mapToInt(Integer::intValue).sum();
            afterRound(13, chansP[0]);
        }else {
            chansP[1] += sVals.stream().mapToInt(Integer::intValue).sum();
            afterRound(13, chansP[1]);
        }
    }

    public void maxiYatzy() {
        ArrayList<Integer> sVals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sVals.add(Integer.valueOf(vals.get(i).getText()));
        }

        Collections.sort(sVals);
        if (player1){
            if (sVals.get(0) == sVals.get(4)) {
                mYatzyP[0] += 50;
            }
            afterRound(14, mYatzyP[0]);
        }else {
            if (sVals.get(0) == sVals.get(4)) {
                mYatzyP[1] += 50;
            }
            afterRound(14, mYatzyP[1]);
        }
    }



    public void roll() {
            Random rnd = new Random();
            for(int i = 0; i < 5; i++) {
                if(!checkbxList.get(i).isSelected()){
                    vals.get(i).setText(String.valueOf(rnd.nextInt(1, 7)));
                }
            }
            rerolls--;
            rollBtn.setText("ROLL - " + rerolls);
            if(rerolls == 0) {
                rollBtn.setDisable(true);
            }

    }

    public void initialize() {
        Arrays.fill(plays1, false);
        Arrays.fill(plays2, false);


    }

}
