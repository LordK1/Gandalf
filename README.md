# Gandalf

Android native application, to satisfy all of my desires and hobbies !!!
Also i've been found how to make test and run latest libraries in whole sides of android application
     e.g UI-UX side or backend side of android applications  
To Try and find better solutions to handle across bottlenecks in android development, 
find new like [ButterKnife] and Dependency Injection With [Dagger 2] on Android Development,
and also to found out how to work with [DateBinding](http://developer.android.com/tools/data-binding/guide.html) in android development
  via support 'MVVM' Design Patterns .
To Use [RxJava] and [RxAndroid] as future of high level android development 
  and many many more new [design patterns](http://www.androiddesignpatterns.com/) in Android development.
this is exactly what i want to know : Android Modern Application Development ! 

NOTICE: Some parts of this project may be not work because i've been try to found what have been in TODO !


## Libraries

- [Data Binding](http://developer.android.com/tools/data-binding/guide.html)

- [Dagger2.0](http://google.github.io/dagger/)

- [Retrofit](http://square.github.io/retrofit/)

- [Picasso](http://square.github.io/picasso/)

- [GSON](https://sites.google.com/site/gson/gson-user-guide)

- [RxJava](https://github.com/Netflix/RxJava)

- [ButterKnife](http://jakewharton.github.io/butterknife/)

- [RxAndroid](https://github.com/ReactiveX/RxAndroid)


## TODO

- make better solution to handle SQLite db and connection too
- resolve scenario 
- make better UI/UX in whole application
- find and usage new frontend Libraries 
- use [LeakCanary](http://github.com/square/leakcanary)
- use [Scalpel](http://github.com/JakeWharton/scalpel)



## [MVVM](http://willowtreeapps.com/blog/mvvm-on-android-what-you-need-to-know/) Design Pattern

The MVVM pattern consists of three parts:

Model – Represents your business logic
View – Displayed content
ViewModel – The object that ties those two together
A ViewModel interface provides two things: actions and data. Actions change the underlying model (click listeners, text changed listeners, etc.), 
and data is the content of that model.

  For example, a ViewModel for an auction page might expose as data an image of the item, a title, description, and price. It might expose as actions the ability to bid, buy, or contact the seller.
  

## Deliverables 

Ooops, This is Self-Experimental project and never have been deliverable in to app stores or somewhere !!! 


## Supported devices

This project will initially target the following devices:

- Nexus 4/5/5X/6
- Samsung GT-I9300 Galaxy S III (upgradeable to Android 4.3)
- Samsung GT-I9100 Galaxy S II (upgradeable Android 4.1)
- Samsung GT-I9505 Galaxy S4 (Android 4.2.2)
- Samsung GT-I9305 Galaxy S III LTE
- Samsung GT-I8190 Galaxy S Mini (Android 4.1)
- HTC ONE
- Samsung SM-G900F Galaxy S5

## Android versions targeted:

- Android 6.0 Marshmallow (API level 23)
- Android 5.0 Lollipop (API level 21) 
- Android 4.4 KitKat (API level 19)
- Android 4.3 Jelly Bean (API level 18)
- Android 4.2 Jelly Bean (API level 17)
- Android 4.1 Jelly Bean (API level 16)
- Android 4.0 Ice Cream Sandwich (API level 15)
 
 
## Codebase

Repository URL: ???

- Master: Main branch containing only versions supposed to be in live production.
Master branch contains only merges from rc branch, after rc branch has gone
through user tests, and is approved as a stable version.

- Develop: The development branch. This is where the actual work happens.
Developers are supposed to checkout this branch in order to develop new code.
Commits will happen often and in small chunks.
Feel free to create your own feature branches out of this branch.

## Environment Setup

Checkout the code from master branch

    git clone [git@github.com:LordK1/Gandalf.git]
    git checkout -b develop origin/develop
    git branch -a


## Important Gradle tasks

1. `gradle assembleDebug`
2. `gradle assembleRelease`
3. `gradle runDebug`
4. `gradle runRelease`
5. `gradle checkstyle`

## References

- [reactivex.io](http://reactivex.io/)
- [Quick Tip: Using Butter Knife to Inject Views on Android](http://code.tutsplus.com/tutorials/quick-tip-using-butter-knife-to-inject-views-on-android--cms-23542)
- [DAGGER 2 - A New Type of dependency injection](https://www.youtube.com/watch?v=oK_XtfXPkqw)
- [Dagger Examples](https://github.com/antoniolg/DaggerExample)
- [Dagger tutorial](https://www.parleys.com/tutorial/architecting-android-applications-dagger)
- [MVVM on Android](http://willowtreeapps.com/blog/mvvm-on-android-what-you-need-to-know/)
- [andytsui](https://andytsui.wordpress.com/category/tutorial/)
- [RxAndroid](https://github.com/ReactiveX/RxAndroid/wiki)
- [DataBinding](https://code.google.com/archive/p/android-binding)
- [Android Data Binding](http://www.codeproject.com/Articles/145203/Android-Binding-Introduction)
- [Retrofit](http://square.github.io/retrofit/)
- [GSON](https://github.com/google/gson)
- [Picasso](http://square.github.io/picasso/)
- [How to Picasso](http://javatechig.com/android/how-to-use-picasso-library-in-android)
- [Picasso Sample](http://www.101apps.co.za/index.php/articles/gridview-tutorial-using-the-picasso-library.html)
- [RxJava introduction](http://blog.danlew.net/2014/10/08/grokking-rxjava-part-4/)
- [Retrofit Introduction](https://gist.github.com/lopspower/004f9295966ab5cb6ef6)
- [vogella ButterKnife](http://www.vogella.com/tutorials/AndroidButterknife/article.html)
- [Android Design Patterns](http://code.tutsplus.com/articles/introduction-to-android-design-patterns--cms-20808)
- [GSON intro](http://www.studytrails.com/java/json/java-google-json-introduction.jsp)
- [GSON Sample](https://www.javacodegeeks.com/2011/01/android-json-parsing-gson-tutorial.html)
- [Common Design Patterns](https://www.raywenderlich.com/109843/common-design-patterns-for-android)
- [U2020](https://github.com/JakeWharton/u2020)
