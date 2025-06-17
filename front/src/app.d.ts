// See https://svelte.dev/docs/kit/types#app.d.ts
// for information about these interfaces
import type { ApiResponse, Game } from '/lib/types/api';
declare global {
	namespace App {
		interface PageData {
			apiresponse:ApiResponse;
			game:Game;
		}
		// interface Error {}
		// interface Locals {}
		// interface PageData {}
		// interface PageState {}
		// interface Platform {}
	}
}

export {};
