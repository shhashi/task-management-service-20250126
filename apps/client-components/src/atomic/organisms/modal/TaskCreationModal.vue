<template>
  <v-dialog v-model="open" max-width="600">
    <v-card title="プロジェクト作成">
      <v-card-item>
        <v-row>
          <v-col cols="4">
            <div class="text-h5">タスク名</div>
          </v-col>
          <v-col cols="8">
            <v-text-field
              v-model="taskName"
              density="comfortable"
              variant="outlined"
            />
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="4">
            <div class="text-h5">説明</div>
          </v-col>
          <v-col cols="8">
            <v-textarea v-model="description" auto-grow></v-textarea>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="4">
            <div class="text-h5">終了日</div>
          </v-col>
          <v-col cols="8">
            <v-menu v-model="dateMenu">
              <template v-slot:activator="{ props }">
                <v-text-field
                  v-model="finishDate"
                  v-bind="props"
                  readonly
                  clearable
                >
                </v-text-field>
              </template>
              <v-date-picker v-model="picker"></v-date-picker>
            </v-menu>
          </v-col>
        </v-row>

        <v-btn
          type="submit"
          color="white"
          block
          class="mt-4"
          @click="closeModal"
        >
          キャンセル
        </v-btn>

        <v-btn
          type="submit"
          color="primary"
          block
          class="mt-4"
          @click="submit"
          :disabled="!isInput || isSubmitted"
        >
          作成
        </v-btn>
      </v-card-item>
    </v-card>
  </v-dialog>
</template>

<script setup lang="ts">
import { computed, ref } from "vue";
import axios from "axios";
import type { Task } from "@/lib/view/ViewType";

const url: string = import.meta.env.VITE_SERVICE_URL;

type Emits = {
  (event: "createdTask", value: Task): void;
};
const emits = defineEmits<Emits>();

const open = defineModel<boolean>("open", { default: false });

type Props = {
  projectId: string;
};
const { projectId } = defineProps<Props>();

const taskName = ref<string>();
const description = ref<string>();
const finishDate = computed(() => {
  return finishRawDate.value!!.substring(0, 10);
});
const finishRawDate = ref<string>(new Date().toISOString());

const picker = computed<Date>({
  get: () => {
    return new Date(finishRawDate.value);
  },
  set: (value: Date) => {
    finishRawDate.value = value!!.toISOString();
  },
});

const dateMenu = ref(false);
const isSubmitted = ref<boolean>(false);

const isInput = computed(() => {
  return !!taskName.value;
});

function closeModal() {
  open.value = false;
}

async function submit() {
  isSubmitted.value = true;

  const response = await axios.put(url + "/api/tasks", {
    taskName: taskName.value,
    description: description.value,
    finishDate: finishRawDate.value,
    projectId: projectId,
  });

  if (200 <= response.status && response.status < 300) {
    const eventValue: Task = {
      taskId: response.data.taskId,
      taskName: response.data.taskName,
    };
    emits("createdTask", eventValue);
  }

  if (400 <= response.status && response.status < 600) {
    alert(
      "タスクの作成に失敗しました。時間を開けて再度実行してください。",
    );
    isSubmitted.value = false;
  }

  closeModal();
}
</script>
