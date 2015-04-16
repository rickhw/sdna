

# How to launch in Eclipse

* On OS X: Run -> select "sdna-ui-mac"
* On Windows: Run -> select "sdna-ui-win32"

# Development

* Eclipse 4.3+
* JDK 1.6+
* swt.jar (already in /lib/swt-mac-4.4.jar, /lib/swt-win32.jar)


# Known issues

1. Get exception: ***WARNING: Display must be created on main thread due to Cocoa restrictions.

http://stackoverflow.com/questions/22273960/swt-exception-when-running-jar-exception-in-thread-main-org-eclipse-swt-swtex

> -XstartOnFirstThread