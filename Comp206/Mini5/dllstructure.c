#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Node {
	int data;
	struct Node *next;
	struct Node *prev;
}Node;

Node *head;

struct Node* create_dll_from_array(int *array, int size) { 
	struct Node *temp = malloc(sizeof(struct Node));
	temp->data = array[0]; 
	temp->next = NULL;
	temp->prev = NULL;
	head = temp;

	for (int i=1; i<size; i++) { 
		struct Node *temp2 = malloc(sizeof(struct Node));
		temp2->data = array[i];
		temp->next = temp2;
		temp2->prev = temp;
		temp2->next = NULL;
		temp = temp2;

	}
	return head;
}

void print_dll(Node* head) { 
	while(head!=NULL) {
      printf("%d\t",head->data);
      head=head->next;
    }
    printf("\n");
}

void insert_after(Node* head, int valueToInsertAfter, int valueToBeInserted) { 
	Node *temp =malloc(sizeof(struct Node));
	temp->data = valueToBeInserted;

	while(head!=NULL) { 
		if (head->data == valueToInsertAfter) {
			temp->prev = head;
			temp->next = head->next;
			head->next = temp;
			break;
		}
		if (head->next==NULL) { 
			head->next = temp; 
			temp->prev = head;
			temp->next = NULL;
			head = temp;
		}
		head = head->next;
	}
}

void delete_element(Node* head, int valueToBeDeleted) { 
	while(head!=NULL) {
		if (head->data==valueToBeDeleted) {
			if (head->prev==NULL) {
				*head=*head->next;
				free(head->prev);
				head->prev=NULL;
			}
			else {
				head->prev->next = head->next;
			}
			break;
		}
		head = head->next;
	}
}

void sort_dll(Node* head) {
	Node *temp;
	Node *temp2 = NULL;
	int done;

	do {
		temp = head;
		done = 0;

		while (temp->next!=temp2) {
			if (temp->data>temp->next->data) {
				int temp3 = temp->data;
				temp->data = temp->next->data;
				temp->next->data = temp3;

				done=1;
			}
			temp=temp->next;
		}
		temp2=temp;
	}
	while(done);
}

void free_dll(Node *head) {
	while(head->next!=NULL) {
		head = head->next;
		free(head->prev);
		if (head->next==NULL) {
			free(head);
		}
	}
}

int main() { 
int array[5] = {11,2,7,22,4};
Node* head;

/* Question 1 */
head = create_dll_from_array(array, 5); //size of array is 5

/* Question 2 */
print_dll(head);

// Question 3 
//to insert 13 after the first occurence of 7
insert_after(head, 7, 13);
//to insert 29 after the first occurence of 21
insert_after(head, 21, 29);
print_dll(head);


// Question 4 
delete_element(head, 22);
print_dll(head);
delete_element(head, 11);
print_dll(head);

// Question 5 
sort_dll(head);
print_dll(head); 

// Question 6 
free_dll(head);

return 0;

}