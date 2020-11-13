[Guide to Mardown Language](https://guides.github.com/features/mastering-markdown/)

# GRE math tips:


## Common Trick questions tips

* integers means a whole number, and includes negatives and positives. Do not get tricked 
  and only use positive numbers if the question simply says "integer"
* If the questions uses the word "numbers" this implies you can use decimals. Do not only
  use integers, or whole numbers in this case
* If the word "different" or "distinct" numbers/ integers isnt used, and there are multiple
  variables, make sure to use the same number/integer for each value
* Don't assume anything. Always look for key words as these can give unequivocal insight 
  into exactly what the question is asking.

## picking numbers for comparison

### pick extremes
  * pick the lowest number possible, and then a number much bigger than that
  * dont pick numbers right next to eachother unless the question asks for it


## Quartiles, box plots, percentiles

* to get 1st or lower quartile, take the first half of a set and get median
* to get 3rd or upper quartile, take the second half of set and get median
* For box plots:

     lower quartile        median   upper quartile
min  |-----------------------|------|           max value
|----|                       |      |-----------|
     |-----------------------|------|


## Weighted Averages 

### Example

* For thee, first spot the middle number
* Find the distances between each endpoint and the middle number
* Switch the distances to find the ratio
* if a ratio is in decimal form, convert it to integer

A theatre sells only matinee tickets and evening tickets. The matinee tickets are priced
at $5 and the evening tickets are priced at $9. If the theatre sold 120 tickets for an
average price of $7.50, how many of those tickets evening tickets?

5--------------7.5-------------9
       2.5             1.5
            1.5:2.5 = 3:5
              3+5 = 8
               3/8 : 5/8 * 120 = 45:75

## Prime factorization & Factorization

### Example

If an integer if divisible by both 8 and 15, the the integer is also divisible by which
of the following?

a.16 b.24 c.32 d.36 e.45

* 8 -> 2^3 15 -> 3 x 5

* 16: 2^4, 24: 2^3 x 3 ....
* the answer's prime factors have to be less than or equal to the integer in question
* 24 is the only answer that can satisfy these conditions

### How to find all the factors in an integer

1. Prime factorize
2. add 1 to all exponents, then multiply them together

* How many factors in 999

* 999: 3^3 x 37^1
*        |      | 
*        v      v
*        4 x    2 = 8

### Another common Example


## percentage:

* a as a percentage of b is a/b x 100

* A was approximately what percent greater than B?
* B is after the word than
* ((A-B)/B)  x 100

## Remainders

### Example

If x is divided by 6, the remainder is 3.

* If x is divided by 6[6 times tables] the remainder is 3[plus 3].
* 6x0+3 = 3
* 6x1+3 = 9
* 6x2+3 = 15
* 6x3+3 = 21

## Functions

* Sometimes, substituting answer choices is faster than trying to solve 
  difference of squares.
* Also, the harder a function question gets the better it is to pick numbers in answers

### Example

F(x) = x^2 + x
F(2t) = 30

* 4t^2 + 2t -30 = 0
* Substitute in all answers here.

### Harder Example

       | x - 1 if x is even
f(x) = | 
       | x + 1 if x is odd

f(a) + f(b) = a + b

* To answer this question correctly, be sure to use the answers, and plug into the variables.
  Try out each one until one fits. This is the only way to do it really.

## Triangles:

### Each side of a triangle **MUST** be:
* less than the sum of the other two sides
* greater than the difference between the other two sides

### Shapes with 3+ sides:

#### Each side MUST be:
* less than the sum of all sides


## Combinations:

**(n!)/((n-r)!r!)**

* n = total from which we are picking out of the group or biggest number
* r = size of group being picked out/selecting from total or smaller number

*tip to remember*: terms on the bottom will always add up to the term at the top

**different from permutations**: permutations are for when the _order_ matters

* __(n!)/(r!)__

### Good question:
* There are 6 women and 5 men. We want to select 2 women and 2 men. How many ways are there
of selecting 2 women AND 2 men from the people available?

  * AND = multiple
  * OR = add

  * 6!/(4!2!) = 15
  * 5!/(3!2!) = 10
  * 15*10 = 150


## Probability:

### Example:
* 3 red beads, 5 green beads. If 2 beads are picked at random AND without replacement, 
1. Prob of picking 2 greens? 2. Prob of picking 2 same colors? 2 diff colors?

  * AND = multiple
  * OR = add

  1. green and green = 5/8 X 4/7 = 5/14
  2. Red and red OR green and green = 5/8 X 4/7 + 3/8 X 2/7 = 13/28
  3. red and green OR green and red = 3/8 X 5/7 + 5/8 X 3/7 = 15/28


## Median:

* Median = middle term of a set of _ascending order sorted list_ of numbers
* mean = average
* if even set of numbers, take 2 medians and divide by 2

### What is the median of all the positive integers less than 75?
* (Biggest + smallest)/2
*For any consecutive list, median = mean*

### Median of Incomes of 25 people

| Income Range | Frequency |
| ------------ | --------- |
| $0-$20k      | 6         |
| $20k-$40k    | 7         |
| $40k-$60k    | 7         |
| $60k-$100k   | 4         |
| $100k+       | 1         |

#### Median salary in what range?

* (num+1)/2 = 13 _This is how you find who the person in the middle is_
* 13th person is in $20k-$40k range


## Standard Deviation

median and range (unless 0, every value is same, meaning std dev is 0) has 
nothing to do with Std dev

* *Formula for SD* = sqrt(sum(abs(value-mean))^2)/num_values)

* sum= sum for each value
* value = arbitrary value
* mean = mean of all values
* num_values = number of values


## Difference of two squares

a^2 - b^2 = (a+b)(a-b)

### *Example*

If p = 5^6 - 3^4 what is the sum of unique prime factors of p?

* p = (5^3 + 3^2)(5^3 - 3^2)
* p = (2 x 67)(2 x 2 x 29)
* 2 + 29 + 67 = _98_


## Unit Digit cycles

All numbers from 0-9 go through digit cycles

### *Example* 3^33 ends with what digit?

* 3^1=3, 3^2=9, 3^3=7, 3^4=1,... 3^8=1...3^12=1...3^32=1, 3^33=3

### *Example* What is the remainder when (16^16)(17^17) is divided by 5

* 6^1=6, 6^2= 6, 6^3= 6, 6^16= 6
* 7^1=7, 7^2= 9, 7^3= 3, 7^4= 1 ... 7^16= 1, 7^17=7
* 6x7=2 _2%5=*2*_


## Normal Distribution

*MEDIAN = MEAN in NORMAL DISTRIBUTION*

*Memorize these:*

* 1stdev from mean = 0.34
* 2stdev from mean = 0.14
* 3stdev from mean = 0.02

### *Example* In a set of exam results, 30% is score of 16, 40% is score 0f 27. Which
quantity is bigger, 50% or 38?

* Trick question: Question never mentioned normal distribution. We have no idea what the
shape of the curve looks like.

### *Example* The heights of a group of math teachers are normally distributed around the
mean. If the median height is 175cm and the 84% is 181cm, which height is 2%?

* Draw the curve to help
* median = right in the middle of curve. This means 50% = 175cm
* 84% -50% = 34% or 0.34 which is 1 stdev, or 6cm
* 2% is 3 stdev from mean. 175 - 6 - 6 = _163cm_


## Average speed

* Time = Distance / speed
* hours = Miles/(miles/hour)
* Average Speed = Total Distance / Total Time

### *Example* Philip walked 4 mph on his way to work but took a route twice as long on 
the way back. If his overall average speed was 8mph, what was his speed on the way back?

* Set the distance ourselves: 40 miles
* 8 mph = 120/(10+x)
* 80+8x=120, 8x=40 x = 5 hours
* 80 miles / 5 hours = _16 mph_


## Divisibility

* For dividing by 3 or 9:
  _If the number is divisible by the sum of the divisor's digits, then it is divisible_

* For dividing by 6:
  _Make sure number is divisible by 3 then check if the number is even_

* For dividing by 4:
  _Make sure the last 2 digits are divisible by 4_

* For dividing by 11
  _Start at the left most digit, subtract, add, sub, add.... until the last digit. 
   If its a multiple of 11 u good_


## Work Rates

* You can't add times, you *CAN* add rates
* time and rate always reciprocal

### Mike takes 2 hours to change a lightbulb and John takes 3 hours. If their rates are
combined, how long will it take for them to change 1 lightbulb?

* M rate = 1LB/2hours
* J rate = 1LB/3hours
* MJ rate = 5/6LB per hour
* MJ time = 6/5 hours per lightbulb or 1 hour 12 min

### Working alone at its constant rate, machine _A_ produces _k_ liters of a chemical in 
10 minutes. Working alone at its constant rate, machine _B_ produces _k_ liters of a 
chemical in 10 minutes. Together, how long will they take to product k liters?

* A = k/10
* B = k/15
* AB = 3k/30 + 2k/30
* AB = 5k/30minutes
* AB time = 6 minutes/ 1 k

### Danya takes 2 hours to paint 1/2 of a wall. Aurelia takes 3 hours for 2. If Danya
paints for 3 hours then is joined by Aurelie, how long will it take for them to paint
3 walls?

* D = 1/4W per hour
* A = 2/3W per hour
* AD = 3/12Wph + 8/12Wph = 11/12Wph
9/4 wall = 11/12Wph * T
T = 27/11 hours


## Inequalities

* dont multiply both sides by a variable until you know the sign
* for adding inequalities, make sure they are in same direction


## Square diagonal

The diagonal is X (side length) x sqrt(2) 
 

## Difficult Quant Questions

### Of 20 lightbulbs, 2 are defective. If I select 2 simultaneously at random, what is the 
probabilty that neither will be defective?

_Simultaneously *DOES NOT* mean with replacement. Think of it as two separate choices_

* d = 2/20, w = 18/20
* ww = 18/20 x 17/19 = 153/190

### What is the least positive integer that is not a factor of 25! and not a prime number?

a)26 b)28 c)36 d)56 e)58

* What is the first prime number above 25? 29. Answer is e.

