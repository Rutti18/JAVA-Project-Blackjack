// package com.blackjack;

// import java.security.cert.TrustAnchor;
import java.util.Scanner;

// import javax.print.FlavorException;

public class Blackjack {

    public static void main(String[] args) {

        System.out.println("Welcome to Blackjack!");

        // Create the playing deck
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        double playerMoney = 100.00;

        Scanner userInput = new Scanner(System.in);

         // Game loops 
         while(playerMoney > 0){

            System.out.println("You have $" + playerMoney + ",  How much would you like to bet?");
            double playerBet = userInput.nextDouble();

            if(playerBet > playerMoney){
                System.out.println("You cannot bet more than what you have, please leave...");
                break;
            }

            boolean endRound = false;


            // Create hands for the player and the dealer - hands are created from methods that are made in the deck class
            // Player gets two cards
            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            // Deale gets two cards
            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            while(true){
                System.out.println("Your hand :");
                System.out.println(playerDeck.toString());
                System.out.println("Your hand is valued at : " + playerDeck.cardsValue());
                
                // Dealer hand
                System.out.println("Dealer hand : " + dealerDeck.getCard(0).toString() + " and [Hidden]");
                
                //What player wants to do (1) Hit or (2) Stand?
                System.out.println("Would you like to (1) Hit or (2) Stand?");
                int response = userInput.nextInt();

                // They hit
                if(response == 1){
                    playerDeck.draw(playingDeck);
                    System.out.println("Your draw a: " + playerDeck.getCard(playerDeck.deckSize() -1 ). toString() );

                    // Bust if > 21
                    if(playerDeck.cardsValue() > 21){
                        System.out.println("Bust. Currently valued at : " + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }

                if(response == 2){
                    break;
                }

            }

            //Reveal Dealer Cards
            System.out.println("Dealer cards :" + dealerDeck.toString());

            //See if dealer has more points than player
            if((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false){
                System.out.println("Dealer beats you!");
                playerMoney -= playerBet;
                endRound = true;
            }

            // Dealer draws at 16, stand at 17
            while((dealerDeck.cardsValue() < 17) && endRound == false){
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer draw " + dealerDeck.getCard(dealerDeck.deckSize() -1 ).toString());
            }

            //Display total value for Dealer
            System.out.println("Dealer's hand is valued at :" + dealerDeck.cardsValue());

            //Determine if dealer busted
            if((dealerDeck.cardsValue() > 21)  && endRound == false){
                System.out.println("Dealer busts ! You win...!!");

                playerMoney += playerBet;
                endRound = true;
            }

            // Determine if push
            if ((playerDeck.cardsValue() == dealerDeck.cardsValue())  && endRound == false){
                System.out.println("Push");
                endRound = true;
            }

             
            else if (endRound == false){
                System.out.println("You loose the hand. ");
                playerMoney -= playerBet;
                endRound = true;
            }

            playerDeck.moveAllToDeck(playingDeck);           // CHANGES MADE
            dealerDeck.moveAllToDeck(playingDeck);
            System.out.println("End of hand");

         }

         System.out.println("Game over! You are out of money.");


        
        
              
       
       
        
        
    }
}