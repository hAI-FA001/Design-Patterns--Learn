# to make an object interface with another
# acts as a bridge b/w 2 incompatible interfaces

class UsbCable:
    def __init__(self):
        self.isPlugged = False
    
    def plugUsb(self):
        self.isPlugged = True

class UsbPort:
    def __init__(self):
        self.portAvailable = True
    
    def plug(self, usb: UsbCable):
        if self.portAvailable:
            usb.plugUsb()
            self.portAvailable = False

class MicroUsb:
    def __init__(self):
        self.isPlugged = False
    
    def plugMicroUsb(self):
        self.isPlugged = True

class MicroToUsbAdapter(UsbCable):
    def __init__(self, microUsb: MicroUsb):
        self.microUsb = microUsb
    
    def plugMicroUsb(self):
        self.microUsb.plugMicroUsb()
    
    def plugUsb(self):
        self.plugMicroUsb()


if __name__ == "__main__":
    usbCable = UsbCable()
    microUsb = MicroUsb()
    usbPort1 = UsbPort()
    usbPort2 = UsbPort()

    # can plug cable directly
    usbPort1.plug(usbCable)

    # can't do this
    # usbPort2.plug(microUsb)
    
    # use adapter
    usbPort2.plug(MicroToUsbAdapter(microUsb))
