book = open('encryptedBook.txt', 'r')
newBook = open('decrytpedBook.txt', 'w')
letterFrequency = [0.0817, 0.0149, 0.0278, 0.0425, 0.127, 0.0223, 0.0202, 0.0609, 0.0697, 0.0015, 0.0077, 0.0402, 0.0241, 0.0675, 0.0751, 0.0193, 0.0009, 0.0599, 0.0633, 0.0906, 0.0276, 0.0098, 0.0236, 0.0015, 0.0197, 0.0007]

# insert your code here


def encrypt(string, shift):
    '''
    This function encrypts the string using the shift provided.
    It also excludes special characters and numbers.
    :param string: string
    :param shift: int
    :return: string
    '''
    cipher = ''
    for char in string:
        # Leave blanks and non alphabets alone
        if not char.isalpha():
            cipher = cipher + char
        elif char == ' ':
            cipher = cipher + char
        # encrypt the alphabets
        elif char.isupper():
            cipher = cipher + chr((ord(char) + shift - 65) % 26 + 65)
        else:
            cipher = cipher + chr((ord(char) + shift - 97) % 26 + 97)

    return cipher


def decrypt(string, shift):
    '''
    This function decrypts the string using the shift provided.
    It also excludes special characters and numbers.
    :param string: string
    :param shift: int
    :return: string
    '''
    cipher = ''
    for char in string:
        # Leave blanks and non alphabets alone
        if not char.isalpha():
            cipher = cipher + char
        elif char == ' ':
            cipher = cipher + char
        # decrypt the alphabets
        elif char.isupper():
            cipher = cipher + chr((ord(char) - shift - 65) % 26 + 65)
        else:
            cipher = cipher + chr((ord(char) - shift - 97) % 26 + 97)

    return cipher



def print_combinations(string):
    """
    This function prints all the combinations of that string using the encrypt
    funciton define above. The shift values change between 1 and 25.
    :param string: string
    :return: None
    """
    for i in range(1,26):
        print(encrypt(string, i))



book.close()
newBook.close()
