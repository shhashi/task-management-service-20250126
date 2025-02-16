export enum ViewTypes {
  Home,
  SearchTask,
  MyPersonHour,
  Project,
}

export type Project = {
  projectId: string;
  projectName: string;
};

export type ViewType = {
  viewType: ViewTypes;
  project?: Project;
};
