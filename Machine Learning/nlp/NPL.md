## Parts of speech

Use this [link](https://arts.uottawa.ca/writingcentre/en/hypergrammar) to get a crash course on grammar.

__Verb__

Denotes action e.g. runs, bites. A little more complex example ==> "Karl Creelman bicycled around the world in 1899, but his diaries and his bicycle _were destroyed_." where _were destroyed_ denotes the action so that is the verb.


__Noun__

Word used to name a person, animal, place, thing, and abstract idea.

e.g.
- Late last `year` our `neighbours` bought a `goat`.
- `Portia White` was an `opera singer`.
- The `bus inspector` looked at all the `passengers' passes.`

Many common nouns, like "engineer" or "teacher," can refer to men or women.

Nouns will often add -s or -es for plurels.

- When Matthew was small he rarely told the `truth` if he thought he was going to be punished.
- Many people do not believe that `truths` are self-evident.

_Possessive Nouns_

- The red suitcase is `Cassandra's`.
- The only luggage that was lost was the `prime minister's.`
- The exhausted recruits were woken before dawn by the - `drill sergeant's` screams.
The `miner's` face was covered in coal dust.


There are various types of nouns

- Proper noun
- Common noun
- Concrete noun
- Abstract noun
- Countable
- Non countable
- Collective

__Pronoun__

A pronoun can replace a noun or another pronoun e.g. he, which, none, you.

__Adjective__

An adjective modifies a noun or a pronoun by describing, identifying, or quantifying words. An adjective usually precedes the noun or the pronoun which it modifies.

- The `truck-shaped` balloon floated over the treetops.
- Mrs. Morrison papered her `kitchen` walls with `hideous` - wall paper.
- The `small` boat foundered on the `wine dark` sea.


__Adverb__

An adverb can modify a verb, an adjective, another adverb, a phrase, or a clause. An adverb indicates manner, time, place, cause, or degree and answers questions such as "how," "when," "where," "how much".

- The seamstress `quickly` made the mourning clothes.
- The midwives waited `patiently` through a long labour.

__Preposition__

A preposition links nouns, pronouns and phrases to other words in a sentence. The word or phrase that the preposition introduces is called the object of the preposition.

- The book is `on` the table.
- She read the book `during` class.
- The children climbed the mountain `without` fear. (In this sentence, the preposition "without" introduces the noun "fear.")


__Conjunction__

You can use a conjunction to link words, phrases, and clauses, as in the following example:

- I ate the pizza `and` the pasta.
- Call the movers `when` you are ready.
- `After` she had learned to drive, Alice felt more independent.

__Interjection__

An interjection is a word added to a sentence to convey emotion. It is not grammatically related to any other part of the sentence.

- `Hey!` Put that down!
- I heard one guy say to another guy, "He has a new car, - `eh`?"
- I don't know about you but, `good lord`, I think taxes are - too high!

---

## Regular Expression

The most basic tool we have to process text is `regular expression`. They are heavily used even in ML classifiers etc... as features.

- Disjunction - Letters inside [] - e.g. [wW] either w or W or ranges e.g. [A-Z] or [a-z] or [0-9] or [A-Za-z]
- Negation - `^` - [^A-z] - not a capital letter, only if occurs right after a `[`.
- OR - `|` 
- `?` - previous character is optional e.g. `colou?r` will match with our without the `u`
- `*` - 0 or more of previous character e.g. `oo*h` --> oh, ooh, oooh, ooooh, ... 
- `+` - ` 1 or more of previous character
- `.` - means any character e.g. `beg.n` --> begin, began begun beg4n etc... 
- `^` - Begining of line e.g. `^[A-Z]` Capital letter at begining of line 
- `$` - End of line e.g. `[A-Z]$` Capital letter at end of line



You can use regular expression to match lots of text but still you will deal with precesion vs recall.

- Increase accuracy or precesion (minimizing false positive)
- Increase coverage or recall (minimize false negetive)

---

## Word Tokenization

Breaking down a sentence into word tokens.

- Some sentences will have fragments, filled pauses like uh or aah --> should we consider these as tokens ?
- Lemma : same stem, same word sense etc... e.g. cat, cats = same lemma
-  Wordforms : cat and cats = different wordforms

_How many words ?_

- N = # of tokens --> an instance of vocabulary type in that text. e.g. total # of words in a sentence
- V = vocabulary = set of types. $|V|$ is the size of vocabulary. --> how many unique words there are ? Types --> could depend on if you count 2 different words with same lemma as 1 or multiple.

There are various issues in Chinese / German / Japanese where you have no concept of word or no spaces between them, the things get a little tough there.

---

## Normalize or Stemming

_Normalization_

- Implicitly define equivalence class of terms ==> U.S.A = USA e.g. remove all `.`
- Asymmetric expansion. (Powerful but less efficient)

We generally use the first one which is simple but want to pay attention to case folding e.g. US vs us.

We also want to do Lemmatization (reduce to base form) e.g. am, are, is --> be | car, cars, car's, cars' --> car

Morphemes : Smallest unit that makes up a word e.g. Stem (the core meaning bearing units), affixes (bits and pieces that adhere to stems, they often have gramatical functions)

_Stemming_ 

- Reduce terms to their stems.
- Its crude chopping of affixes
- Language dependent
- Its a simplification of Lemmatization