#2020 Robot Code


How to implement suppliers:  
In RobotContainer, you can pass a lambda for a supplier: 
private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem, () -> xbox.getTriggerAxis(Hand.kRight));

The parameter is DoubleSupplier on ExampleCommand:
 public ExampleCommand(ExampleSubsystem subsystem, DoubleSupplier rightTrigger)
 
 Lastly the value can be obtained by rightTrigger.getAsDouble()
