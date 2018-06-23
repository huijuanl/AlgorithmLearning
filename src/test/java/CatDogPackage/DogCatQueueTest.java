package CatDogPackage;

import org.junit.Test;

import static org.junit.Assert.*;

public class DogCatQueueTest {
    @Test
    public static void main(String[] args) {
        DogCatQueue test = new DogCatQueue();

        Pet dog1 = new Dog();
        Pet cat1 = new Cat();
        Pet dog2 = new Dog();
        Pet cat2 = new Cat();
        Pet dog3 = new Dog();
        Pet cat3 = new Cat();

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

//        while (!test.isDogQueueEmpty()) {
//            System.out.println(test.pollDog().getPetType());
//        }
        while (!test.isEmpty()) {
            System.out.println(test.poll().getPetType());
        }
    }

}