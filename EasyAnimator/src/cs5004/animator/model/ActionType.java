package cs5004.animator.model;

/**
 * This is an enumerated type for an Action Type. It indicates the type of change occurring for the
 * shape during the time period of an action in the animation. These can be either a change in size,
 * a movement or a change in color.
 */
public enum ActionType {
  RESIZE_X(),
  RESIZE_Y(),
  MOVE_X(),
  MOVE_Y(),
  RECOLOR_R(),
  RECOLOR_G(),
  RECOLOR_B();

}

