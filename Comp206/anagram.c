#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]) {
	char *word1 = argv[1];
	char *word2 = argv[2];
	
	int counter=0;
	int space=0;
	for (int i=0; i<strlen(word1); i++) {
		if (word1[i]==' ') {
			space++;
		}
		for (int j=0; j<strlen(word2); j++) {
			if (word1[i] == word2[j] && word1[i]!= ' ') {
				counter++;
				word2[j] = ' ';
				break;

			}
		}
	}

	if (counter==(strlen(word1)-space)) {
		return 0;
	}
	else {
		return 1;
	}
	

}