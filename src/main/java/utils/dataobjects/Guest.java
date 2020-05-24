package utils.dataobjects;

import utils.dataProviders.RandomHelper;

public class Guest {

	
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String mobileNumber;
	private String stateName;
	private String cityName;
	private String zipCode;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public static Guest guest() {
		
		String[][] data = utils.dataProviders.DataInputProvider.getSheet("Guest_INFO",0);

		int num = RandomHelper.getRandomInt(1, 500);
		Guest guest = new Guest();

		for (int row = 0; row < data.length; row++)
			if (row == num) {
				guest.setFirstName(data[row][0]);
				guest.setLastName(data[row][1]);
				guest.setAddress(data[row][2]);
				guest.setCityName(data[row][3]);
				guest.setStateName(data[row][4]);
				guest.setZipCode(data[row][5]);
				guest.setMobileNumber(data[row][7]);
				guest.setEmail(data[row][8]);
			}

		return guest;
	}
}
