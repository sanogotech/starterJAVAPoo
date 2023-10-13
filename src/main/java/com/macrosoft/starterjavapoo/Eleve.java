package com.macrosoft.starterjavapoo;

public class Eleve {
	
	private String nom;
    private int maths;
    private int physique;
    private int francais;
    private int anglais;

    public Eleve(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNotes(int maths, int physique, int francais, int anglais) {
        if (maths < 0 || maths > 20 || physique < 0 || physique > 20 || francais < 0 || francais > 20 || anglais < 0 || anglais > 20) {
            throw new IllegalArgumentException("Les notes doivent être comprises entre 0 et 20.");
        }
        this.maths = maths;
        this.physique = physique;
        this.francais = francais;
        this.anglais = anglais;
    }

    public int getMaths() {
        return maths;
    }

    public int getPhysique() {
        return physique;
    }

    public int getFrancais() {
        return francais;
    }

    public int getAnglais() {
        return anglais;
    }

    public double calculerMoyenne() {
        return (maths + physique + francais + anglais) / 4.0;
    }

}
