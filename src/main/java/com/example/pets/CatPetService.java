package com.example.pets;

public class CatPetService implements PetService{
    @Override
    public String getPetType() {
        return "Cats are the best";
    }
}
