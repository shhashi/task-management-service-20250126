import axios from "axios";

type Task = {
  taskId: string;
  taskName: string;
  status: number;
  statusName: string;
  projectId: string;
  finishDate: string;
  assignedAccountID: number;
  assignedAccountName: string;
};

type Response = {
  tasks: Array<Task>;
};

const url: string = import.meta.env.VITE_SERVICE_URL;

export async function fetchTasks({
  projectId,
}: {
  projectId: string;
}): Promise<Array<Task>> {
  const response = await axios.get<Response>(
    url + "/api/tasks?projectId=" + projectId, // TODO パラメータをもう少しいい感じに設定できるようにしたい
  );
  return response.data.tasks;
}
