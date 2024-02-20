package rca.bank;

public class Customer {
    private int id;
    private String name;
    private String email;
    private int age;
    private String phoneNumber;
    private String password;

    public Customer(String name, String email,int age,String password,String phoneNumber) {
        this.name = name;
        this.email = email;
        this.age=age;
        this.password=password;
        this.phoneNumber=phoneNumber;   
        }

    public Customer(int id,String name, String email,String password,String phoneNumber) {
    	this.id=id;
        this.name = name;
        this.email = email;
        this.password=password;
        this.phoneNumber=phoneNumber;   
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", age=" + age + ", phoneNumber="
				+ phoneNumber + "]";
	}

	
 
}
