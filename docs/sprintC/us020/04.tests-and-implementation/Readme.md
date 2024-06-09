# US006 - Create a Task 

## 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the GreenSpace class with null values. 

	@Test
    void ensureNullFieldsNotAllowed(){
        assertThrows(IllegalArgumentException.class, () ->
                new GreenSpace(null, null, 50, null));
    }
	

**Test 2:** Check that it is not possible to create an instance of the Task class with a non-positive area. 

	@Test
    void ensureNonPositiveAreaNotAllowed(){
        assertThrows(IllegalArgumentException.class, () ->
                new GreenSpace("Hello", "Somewhere", 0, GreenSpaceType.GARDEN));
    }

**Test 3:** Check that it is not possible to add duplicate green spaces.

	@Test
    void ensureAddingDuplicateGreenSpaceFails() {
        //Arrange
        GreenSpaceRepository greenSpaceRepository = new GreenSpaceRepository();
        greenSpaceRepository.add("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);

        //Act
        Optional<GreenSpace> duplicateGreenSpace = greenSpaceRepository.add("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);

        //Assert
        assertTrue(duplicateGreenSpace.isEmpty());
    }


## 5. Construction (Implementation)

### Class GreenSpace

```java
public GreenSpace(String name, String address, int area, GreenSpaceType type) {
    if(name == null || address == null || type == null){
        throw new IllegalArgumentException("Null fields not allowed.");
    }
    name = name.trim();
    address = address.trim();
    if(name.isBlank() || address.isBlank()){
        throw new IllegalArgumentException("Blank fields not allowed.");
    }
    if(area <= 0){
        throw new IllegalArgumentException("Area value must be a number greater than 0.");
    }
    char[] nameCharacters = name.toCharArray();
    for(char c : nameCharacters){
        if(!Character.isLetter(c) && !Character.isWhitespace(c)){
            throw new IllegalArgumentException("Green space name can only contain letters and whitespaces.");
        }
    }
    this.name = name;
    this.address=address;
    this.area = area;
    this.type = type;
    if(ApplicationSession.getInstance().getCurrentSession().getUserID() == null){
        creatorEmail = null;
    }else{
        creatorEmail = ApplicationSession.getInstance().getCurrentSession().getUserEmail();
    }
}

@Override
public boolean equals(Object o){
    if (!(o instanceof GreenSpace)){
        return false;

    }
    GreenSpace s =(GreenSpace) o;
    if (s.getName().equalsIgnoreCase(name) ||s.getAddress().equalsIgnoreCase(address)){
        return true;
    }return false;
}
```

### Class GreenSpaceRepository

```java
public Optional<GreenSpace> add(String name, String address, int area, GreenSpaceType type){

    GreenSpace greenSpace=new GreenSpace(name, address, area, type);

    if(greenSpaces.contains(greenSpace)){
        return Optional.empty();
    }

    greenSpaces.add(greenSpace);
    return Optional.of(greenSpace);
}
```