# The App

The aim of this application is to monitor when the device boots up and to set up a repeating task for displaying notifications containing boot event details. Users can dismiss these notifications, which will prompt the app to reschedule them accordingly.

## Boot Event

The application must listen for the `RECEIVE_BOOT_COMPLETED` event.
Once the event is triggered, the application must persist such an event with the relevant information (required in the next sections).

## Application

Any time the application is awakened, it must schedule the action to be executed.
General Information About the Action:

- It must run every 15 minutes (`15 min rule`).
- On run, it must show the notification with the "special" body.
- Restore the notification with the updated information on the boot event **only if** the notification was present before the (re)boot.
Notification Dismiss Action:
- It should be rescheduled based on the configuration and shouldn’t take into account the 15 minutes rule.
Note:
The title, channel, and other missing information do not have any limitations/requirements.

## “Special” Body

There are 3 possible texts within the notification body:

1. **If no boot events were detected within the app’s lifetime:**  
   The body text should be:  
   `"No boots detected"`

2. **If only 1 boot event was detected:**  
   The text must be:  
   `"The boot was detected = ${date_of_the_boot_event}"`  
   `date_of_the_boot_event` is in the format `DD/MM/YYYY HH:MM:SS`.

3. **If multiple events were detected:**  
   The text should be:  
   `"Last boots time delta = ${time_between_2_last_boot_events}"`  
   `time_between_2_last_boot_events` must be the delta between the last and pre-last boot events.

### Notification Dismiss Action (TODO)

Upon dismissal, the notification should be rescheduled to reappear according to the following setup:

- **Total dismissals allowed:** 5
- **Interval between dismissals:** `Dismissal count * 20 minutes`
- **Notification content:** Must adhere to the "Special" body requirements.
- **If the total number of dismissals exceeds the limit,** notification scheduling should revert to the 15-minute rule.  
  For example, if the user dismisses the notification 5 times, the next notification should appear in 15 minutes, and further dismissals should trigger the behavior outlined above.

## UI

- Any `View` component can be used to display the info. For example, it can be a simple `TextView`.

The UI should show different text depending on the information:

1. **If no boot events were triggered within the app’s lifetime:**  
   The text must be:  
   `"No boots detected"`

2. **If there were boot events during the app’s lifetime:**  
   The text view must be populated with the info of each boot event:
    - Date of the boot events (e.g., `01/04/2024`)
    - Number of boot events per day (e.g., `5, 2, 3`).

3. **Should have the ability to change the “Total dismissals allowed” and “Interval between dismissals” using UI components** (can be simple edit texts).

Example of the UI text 
- 01/04/2024 - 5
- 02/04/2024 - 10
- 06/04/2024 - 3
