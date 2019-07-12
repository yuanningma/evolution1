# evolution1

This is a silly little program that's supposed to generate the String you input through an evolutionary algorithm. I tried playing
around with a few different fitness functions and crossover methods, but I haven't gotten it to be very fast yet. A test on 
"hello world" took almost 80000 generations to generate, so...yeah, not great.

Originally I only allowed the best 20 individuals to reproduce, but I've since changed to allow for everyone to reproduce and for
random Strings to be generated every so often. If you can improve it, please do; I'm curious as to what is taking so long. I'm guessing
it's either my fitness function or the way I pair off individuals to reproduce, but who knows.

I believe the issue may be that I have a set probability of mutation, but I apply it to every character. It should only be once per word, or I need to make the mutation rate far, far lower than it currently is.
