package CatDogPackage;

import java.util.LinkedList;
import java.util.Queue;

//已经有的类:Pet,Dog,Cat
class Pet {
    private String type;

    public Pet(String type) {
        this.type = type;
    }

    public String getPetType() {
        return this.type;
    }

}

class Dog extends Pet {
    public Dog() {
        super("Dog");
    }
}

class Cat extends Pet {
    public Cat() {
        super("Cat");
    }
}

//为解决猫狗队列问题新加的类:PetEnterQueue
class PetEnterQueue {
    Pet pet;
    int count;

    public PetEnterQueue(Pet pet, int count) {
        this.pet = pet;
        this.count = count;

    }

    public Pet getPet() {
        return this.pet;
    }

    public int getCount() {
        return this.count;
    }

}

public class DogCatQueue {

    Queue<PetEnterQueue> DogQueue;
    Queue<PetEnterQueue> CatQueue;
    int count;

    public DogCatQueue() {
        DogQueue = new LinkedList<PetEnterQueue>();
        CatQueue = new LinkedList<PetEnterQueue>();
        count = 0;
    }

    public void add(Pet pet) {
        if (pet.getPetType().equals("Dog"))
            this.DogQueue.add(new PetEnterQueue(pet, count++));
        else if (pet.getPetType().equals("Cat"))
            this.CatQueue.add(new PetEnterQueue(pet, count++));
        else throw new RuntimeException("error:not Dog or Cat");

    }

    public boolean isEmpty() {
        return this.DogQueue.isEmpty() && this.CatQueue.isEmpty();

    }

    public boolean isDogQueueEmpty() {
        return this.DogQueue.isEmpty();
    }

    public boolean isCatQueueEmpty() {
        return this.DogQueue.isEmpty();
    }

    public Pet poll() {
        if (!this.DogQueue.isEmpty() && !this.CatQueue.isEmpty()) {
            if (this.DogQueue.peek().getCount() < this.CatQueue.peek().getCount())
                return this.DogQueue.poll().getPet();
            else return this.CatQueue.poll().getPet();

        } else {
            if (!this.DogQueue.isEmpty())
                return this.DogQueue.poll().getPet();
            else if (!this.CatQueue.isEmpty())
                return this.CatQueue.poll().getPet();
            else throw new RuntimeException("error:DogCatQueue is empty");
        }


    }

    public Dog pollDog() {
        if (!this.DogQueue.isEmpty())
            return (Dog) this.DogQueue.poll().getPet();//强制转换
        else throw new RuntimeException("error:DogQueue is empty");

    }

    public Cat pollCat() {
        if (!this.CatQueue.isEmpty())
            return (Cat) this.CatQueue.poll().getPet();//强制转换
        else throw new RuntimeException("error:CatQueue is empty");

    }


}
