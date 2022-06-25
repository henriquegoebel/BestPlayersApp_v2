package com.example.n1_henriquegoebel;

public class Time {

    public String id;
    public String nomeTime;
    public Jogador goleiro;
    public Jogador lateralD;
    public Jogador lateralE;
    public Jogador zagueiroD;
    public Jogador zagueiroC;
    public Jogador meiaV;
    public Jogador meiaD;
    public Jogador meiaE;
    public Jogador meiaA;
    public Jogador atacanteD;
    public Jogador atacanteE;
    public String idUsuario;


    public Time() {
    }

    public Time(String id, String nomeTime, Jogador goleiro, Jogador lateralD, Jogador lateralE, Jogador zagueiroD, Jogador zagueiroC, Jogador meiaV, Jogador meiaD, Jogador meiaE, Jogador meiaA, Jogador atacanteD, Jogador atacanteE, String idUsuario) {
        this.id = id;
        this.nomeTime = nomeTime;
        this.goleiro = goleiro;
        this.lateralD = lateralD;
        this.lateralE = lateralE;
        this.zagueiroD = zagueiroD;
        this.zagueiroC = zagueiroC;
        this.meiaV = meiaV;
        this.meiaD = meiaD;
        this.meiaE = meiaE;
        this.meiaA = meiaA;
        this.atacanteD = atacanteD;
        this.atacanteE = atacanteE;
        this.idUsuario = idUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeTime() {
        return nomeTime;
    }

    public void setNomeTime(String nomeTime) {
        this.nomeTime = nomeTime;
    }

    public Jogador getGoleiro() {
        return goleiro;
    }

    public void setGoleiro(Jogador goleiro) {
        this.goleiro = goleiro;
    }

    public Jogador getLateralD() {
        return lateralD;
    }

    public void setLateralD(Jogador lateralD) {
        this.lateralD = lateralD;
    }

    public Jogador getLateralE() {
        return lateralE;
    }

    public void setLateralE(Jogador lateralE) {
        this.lateralE = lateralE;
    }

    public Jogador getZagueiroD() {
        return zagueiroD;
    }

    public void setZagueiroD(Jogador zagueiroD) {
        this.zagueiroD = zagueiroD;
    }

    public Jogador getZagueiroC() {
        return zagueiroC;
    }

    public void setZagueiroC(Jogador zagueiroC) {
        this.zagueiroC = zagueiroC;
    }

    public Jogador getMeiaV() {
        return meiaV;
    }

    public void setMeiaV(Jogador meiaV) {
        this.meiaV = meiaV;
    }

    public Jogador getMeiaD() {
        return meiaD;
    }

    public void setMeiaD(Jogador meiaD) {
        this.meiaD = meiaD;
    }

    public Jogador getMeiaE() {
        return meiaE;
    }

    public void setMeiaE(Jogador meiaE) {
        this.meiaE = meiaE;
    }

    public Jogador getMeiaA() {
        return meiaA;
    }

    public void setMeiaA(Jogador meiaA) {
        this.meiaA = meiaA;
    }

    public Jogador getAtacanteD() {
        return atacanteD;
    }

    public void setAtacanteD(Jogador atacanteD) {
        this.atacanteD = atacanteD;
    }

    public Jogador getAtacanteE() {
        return atacanteE;
    }

    public void setAtacanteE(Jogador atacanteE) {
        this.atacanteE = atacanteE;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}



//pode criar objeto sem todos atributos. Ver se o Objeto Jogador pode ser null no banco de dados, pior dos casos criar um Objeto fake.
//Criar tela para seleção com Checkbox se possível, o Checkbox puxa um Text que vai reconhece o jogador de acordo com o nome/numero da camiseta(campos obrigatório)
//Deve-se criar uma Activity nova com acesso a partir da lista de jogadores. Pode ser via menu.

//opção de criar apenas uma tabela e inserir nome do Time no jogador e puxando do banco apenar os jogadores com time = nomedotime