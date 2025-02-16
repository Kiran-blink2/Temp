class phonebook:
    def __init__(self):
        self.contacts = {}
    
    def add_contact(self, name, number):
        self.contacts[name] = number
        print(f"Contact {name} added successfully.")
    
    def delete_contact(self, name):
        if name in self.contacts:
            del self.contacts[name]
            print(f"Contact {name} deleted successfully.")
        else:
            print("Contact not found.")
    
    def replace_contact(self, name, new_number):
        if name in self.contacts:
            self.contacts[name] = new_number
            print(f"Contact {name} updated successfully.")
        else:
            print("Contact not found.")
    
    def display_contacts(self):
        if not self.contacts:
            print("Phone book is empty.")
        else:
            print("\nPhone Book:")
            for name, number in self.contacts.items():
                print(f"{name}: {number}")

if __name__ == "__main__":
    phone_book = phonebook()
    
    while True:
        print("\n1. Add Contact\n2. Delete Contact\n3. Replace Contact\n4. Display Contacts\n5. Exit")
        choice = input("Enter your choice: ")
        
        if choice == "1":
            name = input("Enter contact name: ")
            number = input("Enter contact number: ")
            phone_book.add_contact(name, number)
        elif choice == "2":
            name = input("Enter contact name to delete: ")
            phone_book.delete_contact(name)
        elif choice == "3":
            name = input("Enter contact name to replace number: ")
            new_number = input("Enter new contact number: ")
            phone_book.replace_contact(name, new_number)
        elif choice == "4":
            phone_book.display_contacts()
        elif choice == "5":
            break
        else:
            print("Invalid choice. Please try again.")
