export enum ViewTypes {
  Home,
  SearchTask,
  MyPersonHour,
  Project,
  TaskDetail,
}

export type Project = {
  projectId: string;
  projectName: string;
};

export type Task = {
  taskId: string;
  taskName: string;
};

export type ViewType = {
  viewType: ViewTypes;
  project?: Project;
  task?: Task;
};
