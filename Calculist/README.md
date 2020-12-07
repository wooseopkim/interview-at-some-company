# Calculist

### What this is

An Android calculator app. I was given specification for this app as follows:

- The application has two activities.
- For the first activity,
  1. There are two fragments: one for buttons, one for text display.
  2. On digit inputs, it behaves as you'd expect.
  3. On the first operator inputs, it displays the first operand.
  4. On subsequent operator inputs, it displays the result of the previously stored operation.
  5. On 'C' button click, it clears the display. 
  6. On '=' button click, it (first resolves the stored opeartion and then,) repeats the last operation performed.
- For the second activity,
  1. Everything is the same but the result is not displayed until '=' button click.
  2. This was not clear on the specification, but I thought repeating of operations by '=' won't happen on this activity.
- See the spec in code on `SimpleCalculatorTest.kt` for the first and `BasicCalculator.kt` for the other. 
- Use any architectural pattern of my choice.
- The value of text display would survive app restarts and config changes such as screen rotation.
- Write unit tests.

# What I did

- I chose MVVM.

Since I started learning programming with Android Java, I always was interested in trends of Android development.
But guys, seriously, it's the age of web tech. Not that web is better. Actually it sucks. It sucks but makes money. 
I've been short on money and there are many jobs for web development. They're easier to find.
I personally didn't have opportunities on serious Android applications for the last few years.

As I've read, MVP pattern has been de facto standard for Android development. 

I attended GDG DevFest Seoul like a month ago. One of the sessions was about data binding and MVVM pattern.
I learned disadvantages of MVP are that (1) presenters have dependencies on view interfaces,
and (2) presenters trigger view changes, which need obsolete glue code.
With data binding and MVVM architecture these are no more problems because
(1) view models don't need to know about views, and (2) data binding takes care of view updates.

More neutral comparison from [`googlesamples/android-architecture`](https://github.com/googlesamples/android-architecture/tree/todo-mvvm-databinding/#designing-the-app):
> The ViewModel in the MVVM architecture plays a similar role to the Presenter in the MVP architecture. The two architectures differ in the way that the View communicates with the ViewModel or Presenter respectively:
When the app modifies the ViewModel in the MVVM architecture, the View is automatically updated by a library or framework. You can’t update the View directly from the ViewModel, as the ViewModel doesn't have access to the necessary reference.
You can however update the View from the Presenter in an MVP architecture as it has the necessary reference to the View. When a change is necessary, you can explicitly call the View from the Presenter to update it. In this project, you use layout files to bind observable fields in the ViewModel to specific UI elements such as a TextView, or ImageView. The Data Binding Library ensures that the View and ViewModel remain in sync bi-directionally as illustrated by the following diagram.


And I recently worked on several Vue.js apps, so the concept of view models felt the most familiar to me.

# Reviews I got and my thoughts

# What I wonder
