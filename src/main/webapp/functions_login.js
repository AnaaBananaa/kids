	console.log('oi')
	
	var tabs = document.querySelectorAll('.toggle-tab');
	var contents = document.querySelectorAll('.display-content')
	
	function unselect(){
		tabs.forEach((tab)=>{
			tab.classList.remove('is_active');
		})

		contents.forEach((content)=>{
			content.classList.remove('is_active');
		})

	}
		
	tabs.forEach((tab, index) =>{
		tab.addEventListener('click', () => {

			unselect()
					
			tabs[index].classList.add('is_active')
			contents[index].classList.add('is_active')
		});
	})
	
	var buttonsUserType =  document.querySelectorAll(".user-type-button")
	console.log(buttonsUserType)
	buttonsUserType.forEach((button, index) => {
		button.	addEventListener('click', ()=>{
			unselect();
			console.log(index)
			if(index < 1){
				tabs[1].classList.add('is_active')
				contents[2].classList.add('is_active')
			}else{
				tabs[0].classList.add('is_active')
				contents[0].classList.add('is_active')
			}
		})
	})
	