import SwiftUI
import shared

struct ContentView: View {
	let msg = Shared().getMessage()

	var body: some View {
		Text(msg)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}