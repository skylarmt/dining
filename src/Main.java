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
 * Main driver class.
 */
public class Main {

    public static Fork[] forks;
    public static Philosopher[] philosophers;

    public static final Object lock = new Object();
    public static boolean locked = false;
    public static volatile int nextEating = 0;
    public static Visualize gui;

    public static volatile boolean guiStarted = false;
    
    public static void main(String[] args) {
        forks = new Fork[5];
        philosophers = new Philosopher[5];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork(i);
        }

        for (int i = 0; i < philosophers.length; i++) {
            switch (i) {
                case 0:
                    philosophers[i] = new Philosopher(4, 0);
                    break;
                case 1:
                    philosophers[i] = new Philosopher(0, 1);
                    break;
                case 2:
                    philosophers[i] = new Philosopher(1, 2);
                    break;
                case 3:
                    philosophers[i] = new Philosopher(2, 3);
                    break;
                case 4:
                    philosophers[i] = new Philosopher(3, 4);
                    break;
            }
        }
        startGUI();
        while (!guiStarted) {
            
        }
        for (Philosopher p : philosophers) {
            p.start();
        }
    }

    public static void incNextEating() {
        nextEating++;
        if (nextEating > 4) {
            nextEating = 0;
        }
    }

    public static void startGUI() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Visualize.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Visualize.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Visualize.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Visualize.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui = new Visualize();
                gui.setVisible(true);
                guiStarted = true;
            }
        });
    }
}
