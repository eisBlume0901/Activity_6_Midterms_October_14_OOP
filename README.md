# Midterm Problem
[eisBlume0901](https://github.com/eisBlume0901) 
[MyPhillings](https://github.com/MyPhilings)
[0ngskie](https://github.com/0ngskie)

Create a program that will accept the following details and will
provide a comprehensive analysis of the input details from the user:

1. Full name
2. Address
3. Course
4. Favorite Movie (from 2000-2023)
5. Favorite number
6. Favorite movie character (from 2000-2023)
7. Favorite food
8. Birthdate
9. Birthplace
10. Prefer the number of Children

Validate each input and check if the value is not a made-up value e.g. abcdef,xz345.

If the input validation fails. Rerun the program after 20 secs

If the validation still fails - increase each rerun iteration by 5 seconds
5 rerun iteration next num is after 40 secs.

Otherwise, assign the details to their respective POJO (Plain Old Java Object).

Once done, check the following conditions:
1. What is the region of the user's address? (e.g. Cavite -> Region IV-A)
2. What is the classification of the user's course? (e.g. IT -> Technology)
3. How many consonants and vowels are in the user's full name?
4. How many words are in the user's full name?
5. Check if the user's age is Senior, Mid, Teenager, Child or Baby
6. Check what is the zodiac sign of the user
7. Identify the user's fav. movie is action, comedy, or thriller
8. Identify the user's fav. movie character is the main character or supporting character.
9. Get the binary, octal & hexadecimal value of the user's  fav. number
10. Provide the user's own psychological feedback based on the preferred number of children by the user.

Provide a comprehensive report of the user's input details in paragraph form using
the conditions above. Proper indention & spacing should be observed. If the paragraph is
greater than 300 letters insert a next line. Once you provide the comprehensive report
to the user, the user can provide a reply and the program will analyze the reply. Check if the
reply of the user is greater than 30 words -> if yes, ask the user if he/she wants to play a game,
and exit the program if the reply is equal to 30 words, exit the program. If the reply is less than 30 words
redirect the user to play a minesweeper game.

The race car game mechanics:

-> user to input the race distance in meters.

-> user to input if the car is automatic or manual.

-> AI is always automatic.

-> countdown timer (3-2-1) at the start of the race.

-> User controls are gas, break & engine shift (if manual)

-> for manual/automatic cars the shift is up to 6 gears

-> For automatic cars, the shift goes up every increase of 20 in acceleration.

-> acceleration value should be displayed for both the user & AI

-> Provide the winner of the game.

-> The probability for the car to heat up & stop in the middle of the race is 20%

## Features used in the program
* Using file class to store English words, movies, courses paired with industry, and cities paired with region
* Using GUI to create the minesweeper and race car game inspired and referenced from existing source codes

## How to start the program?
1. Go to src folder.
2. Open the `Main.java` file outside the packages.
3. Make sure the following code is present
```java
import userinterface.UserInterface;
public class Main
{
    public static void main(String[] args)
    {
        UserInterface userInterface = new UserInterface();
        userInterface.start();
    }
}
```
4. Run the `Main.java` to execute the program
5. Sample output: https://youtu.be/Hg783YzIutg

## License
Copyright (c) 2023 eisBlume0901

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
OR OTHER DEALINGS IN THE SOFTWARE.
