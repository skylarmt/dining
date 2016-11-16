
/*
 * Copyright (c) 2016, Skylar Ittner, Ricardo Barron-Silva.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * * Neither the name of the copyright holder nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
/**
 *
 * @author skylar
 */
public class Philosopher extends Thread {

    private int fork1 = 0;
    private int fork2 = 0;

    public Philosopher(int fork1, int fork2) {
        this.fork1 = fork1;
        this.fork2 = fork2;
        this.setName("Philosopher " + (fork2 + 1));
    }

    private enum state {
        THINKING,
        EATING
    }

    @Override
    public void run() {
        while (true) {
            synchronized (Main.lock) {
                if (Main.nextEating == fork2) {
                    // Eating
                    System.out.println(this.getName() + " is eating...");
                    Main.gui.setEating(fork2);
                    try {
                        Thread.sleep(1000);
                        System.out.println(this.getName() + " is done eating...");
                        Main.incNextEating();
                        synchronized (Main.lock) {
                            Main.lock.notifyAll();
                        }
                    } catch (InterruptedException ex) {
                    }
                } else {
                    // Thinking
                    System.out.println(this.getName() + " is thinking...");
                    Main.gui.setThinking(fork2);
                    synchronized (Main.lock) {
                        try {
                            Main.lock.wait();
                        } catch (InterruptedException ex) {
                        }
                    }
                    System.out.println(this.getName() + " is done thinking...");
                }
            }
            /*synchronized (Main.forks[fork1]) {
                synchronized (Main.forks[fork2]) {
                    if (Main.forks[fork1].getInUse() || Main.forks[fork2].getInUse()) {
                        think();
                    }
                }
            }
            eat();*/
        }
    }

    private void think() {
        System.out.println(this.getName() + " is thinking...");
        try {
            synchronized (Main.forks[fork1]) {
                Main.forks[fork1].wait(1000);
            }
            synchronized (Main.forks[fork2]) {
                Main.forks[fork2].wait(1000);
            }
        } catch (InterruptedException ex) {

        }
    }

    private void eat() {
        System.out.println(this.getName() + " is eating...");
        synchronized (Main.forks[fork1]) {
            Main.forks[fork1].setInUse(true);
        }
        synchronized (Main.forks[fork2]) {
            Main.forks[fork2].setInUse(true);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        synchronized (Main.forks[fork1]) {
            Main.forks[fork1].setInUse(false);
            Main.forks[fork1].notifyAll();
        }
        synchronized (Main.forks[fork2]) {
            Main.forks[fork2].setInUse(false);
            Main.forks[fork2].notifyAll();
        }
    }

}
