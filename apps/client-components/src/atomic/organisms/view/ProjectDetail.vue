<template>
  <h4>プロジェクト詳細画面</h4>
  <v-btn width="300" @click="taskCreationModalOpen = true">タスク作成</v-btn>
  <TaskCreationModal
    v-model:open="taskCreationModalOpen"
    :project-id="projectId!!"
    @created-task="
      (value) => {
        emits('change:viewType', {
          viewType: ViewTypes.TaskDetail,
          task: value,
        } as ViewType);
      }
    "
  ></TaskCreationModal>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import { type ViewType, ViewTypes } from "@/lib/view/ViewType";
import TaskCreationModal from "@/atomic/organisms/modal/TaskCreationModal.vue";

type Props = {
  projectId?: string;
};
const { projectId } = defineProps<Props>();

type Emits = {
  (event: "change:viewType", value: ViewType): void;
};
const emits = defineEmits<Emits>();

const taskCreationModalOpen = ref(false);

onMounted(() => {
  if (!projectId) {
    alert("プロジェクトIDを取得できませんでした。");
    emits("change:viewType", { viewType: ViewTypes.Home } as ViewType);
  }
});
</script>
