"""
Lets get some terms out of the way.

Tokenizing - word tokenizer, sentence tokenizer. (Group by)

Corpora - Body of text e.g. medical journal

Lexicon - Words and their meaning.

Stop words - The words you don't care about e.g. articles (a, an, the) or words which may be used scarcastically etc...

Steming - Sort of getting the root of the word. e.g. ride, riding

Chunking - Grouping words together or sentence etc... to deduce a meaning. You can chunk up (more abstract) or chunk down (more specific)

Chinking - Removal of something. (pretty much the opposite of chunking)
"""


# ------------------------------------------ # 



import nltk


sentence = """There were many sparrows, of course.  A small group that seemed to keep pretty much to itself consisted entirely of a rare English variety.  There were two regular pairs of bright red cardinals.  One pair Tom had traced to their nest in the big oak that grew in the Burke's front lawn, three houses down.  A family of nuthatches had made a home in a small hollow in the old maple out behind the garage.  Numerous robins and red-winged blackbirds had come and gone throughout the summer."""


"""Tokenizing"""
def ex_tokenizer():
    
    print(nltk.tokenize.sent_tokenize(sentence))
    print(nltk.tokenize.word_tokenize(sentence))

# ex_tokenizer()


"""Stop Words"""
def ex_stop_words():
    stop_words = nltk.corpus.stopwords.words('english')
    words = nltk.tokenize.word_tokenize(sentence)
    return [word for word in words if word not in stop_words]

# ex_stop_words()

"""Steming"""
def ex_stem():
    words = nltk.tokenize.word_tokenize(sentence)
    ps = nltk.stem.PorterStemmer()

    for word in words:
        print(ps.stem(word))

# ex_stem()

"""Parts of speech tagging"""
def ex_part_of_speech_tagging():
    """
    Using sentence tokenizer (it uses unsupervised ML algo)
    """ 
    train_text = nltk.corpus.state_union.raw('2005-GWBush.txt')
    sample_text = nltk.corpus.state_union.raw('2006-GWBush.txt')

    custom_tokenizer = nltk.tokenize.PunktSentenceTokenizer(train_text=train_text)
    tokenized = custom_tokenizer.tokenize(sample_text)

    # print(tokenized)

    try:
        for element in tokenized:
            words = nltk.tokenize.word_tokenize(element)
            pos = nltk.pos_tag(words)
            print(pos)

    except expression as identifier:
        print(str(identifier))

# ex_part_of_speech_tagging()


def ex_chunking():
    """
    Using sentence tokenizer (it uses unsupervised ML algo)
    """ 
    train_text = nltk.corpus.state_union.raw('2005-GWBush.txt')
    sample_text = nltk.corpus.state_union.raw('2006-GWBush.txt')

    custom_tokenizer = nltk.tokenize.PunktSentenceTokenizer(train_text=train_text)
    tokenized = custom_tokenizer.tokenize(sample_text)

    # print(tokenized)

    for element in tokenized:
        words = nltk.tokenize.word_tokenize(element)
        tagged = nltk.pos_tag(words)

        chunkGram = r"""Chunk: {<RB.?>*<VB.?>*<NNP><NN>?}"""

        chunkParser = nltk.RegexpParser(chunkGram)

        chunked = chunkParser.parse(tagged)

        # print(chunked.draw())
        print(chunked)

# ex_chunking()

